/**
 * 
 */
package com.nequi.uts601.sef.service;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nequi.cmm.consumer.exception.CommonUtilException;
import com.nequi.cmm.consumer.util.UtilJSON;
import com.nequi.mdw.common.tracerv7.service.GenericLogger;
import com.nequi.mdw.common.tracerv7.service.LoggerFactory;
import com.nequi.mdw.common.tracerv7.service.ServiceTypeEnum;
import com.nequi.uts601.ejb.exception.MBSException;
import com.nequi.uts601.ejb.factory.IFactory;
import com.nequi.uts601.ejb.factory.impl.CheckAvailabilityFactory;
import com.nequi.uts601.ejb.factory.impl.SendDocumentFactory;
import com.nequi.uts601.ejb.service.ServiceBean;
import com.nequi.uts601.ejb.util.Constant;
import com.nequi.uts601.messaging.services.seiya.MessageRQ;
import com.nequi.uts601.messaging.services.seiya.MessageRS;
import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;
import com.nequi.uts601.util.BuildMessageServiceUtil;

/**
 *
 */
@Path(Constant.FACADE_UTIL_SERVICES_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UtilService {

    private GenericLogger logger;

    @EJB
    private CheckAvailabilityFactory checkAvailabilityFactory;
    @EJB
    private SendDocumentFactory sendDocumentFactory;

    /**
     * 
     */
    @PostConstruct
    public void init() {
        logger = LoggerFactory.getLogger(ServiceTypeEnum.BUSSINES,
                UtilService.class);
    }

    /**
     * Fachada del dominio de negocio para validar horario de disponibilidad de
     * un servicio
     * 
     * @param request
     * @return {@link String}
     */
    @Path(Constant.SERVICE_OPERATION_CHECKAVAILABILITY)
    @javax.ws.rs.POST
    public String checkAvailability(String request) {
        return callService(request, checkAvailabilityFactory);
    }

    /**
     * Fachada del dominio de negocio para generar docuemtnos y enviarlos por
     * mail
     * 
     * @param request
     * @return {@link String}
     */
    @Path(Constant.SERVICE_OPERATION_SEND_DOCUMENT)
    @javax.ws.rs.POST
    public String sendDocument(String request) {
        return callService(request, sendDocumentFactory);
    }

    /**
     * Metodo generico para llamar EJB
     * 
     * @param request
     * @param factory
     * @return {@code String}
     */
    private String callService(String request, IFactory factory) {

        String response = null;
        MessageRQ messageRQ = null;
        MessageRS messageRS = null;
        String messageId = Constant.COMMON_STRING_EMPTY;
        String consumerId = Constant.COMMON_STRING_EMPTY;

        try {

            messageRQ = UtilJSON.parsePayloadWithJaxbAnnotation(request,
                    MessageRQ.class);

            RequestHeaderType header = messageRQ.getRequestMessage()
                    .getHeader();
            if (null != header && null != header.getMessageID()
                    && !header.getMessageID().isEmpty()) {
                messageId = header.getMessageID();
            }
            if (null != header && null != header.getConsumer()
                    && null != header.getConsumer().getId()
                    && !header.getConsumer().getId().isEmpty()) {
                consumerId = header.getConsumer().getId();
            }

            String region = messageRQ.getRequestMessage().getHeader()
                    .getDestination().getServiceRegion();

            String version = messageRQ.getRequestMessage().getHeader()
                    .getDestination().getServiceVersion();

            ServiceBean bean = factory.getServiceBean(region, version);

            response = bean.executeOperation(request);

        } catch (CommonUtilException | MBSException e) {
            logger.traceError(e.getMessage(), e, messageId, consumerId);

            RequestHeaderType headerRQ = null;

            if (null != messageRQ) {
                headerRQ = messageRQ.getRequestMessage().getHeader();
            }

            messageRS = BuildMessageServiceUtil.generateResponse(headerRQ,
                    Constant.ERROR_DEFAULT_CODE,
                    Constant.ERROR_DEFAULT_MESSAGE);
            try {
                response = UtilJSON.parseObjectToString(messageRS);
            } catch (CommonUtilException ex) {
                logger.traceError(Constant.ERROR_TO_PARSE_RESPONSE, ex,
                        messageId, consumerId);
            }
        }
        return response;

    }

}