package com.nequi.uts601.ejb.service.co.seiya.checkavailability;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nequi.cmm.consumer.exception.CommonUtilException;
import com.nequi.cmm.consumer.exception.RestClientException;
import com.nequi.cmm.consumer.registry.ServiceRegistry;
import com.nequi.cmm.consumer.rest.util.CallServiceUtil;
import com.nequi.cmm.consumer.util.UtilJSON;
import com.nequi.mdw.common.jpa.exception.JPAException;
import com.nequi.mdw.common.jpa.model.entities.Parametro;
import com.nequi.mdw.common.jpa.model.entities.ParametroAtributo;
import com.nequi.mdw.common.jpa.service.ParameterJPAService;
import com.nequi.mdw.common.jpa.service.impl.ParameterJPAServiceIMPL;
import com.nequi.mdw.common.tracerv7.service.GenericLogger;
import com.nequi.mdw.common.tracerv7.service.LoggerFactory;
import com.nequi.mdw.common.tracerv7.service.ServiceTypeEnum;
import com.nequi.uts601.ejb.exception.MBSException;
import com.nequi.uts601.ejb.service.ServiceBean;
import com.nequi.uts601.ejb.util.CommonUtil;
import com.nequi.uts601.ejb.util.Constant;
import com.nequi.uts601.messaging.services.seiya.CheckAvailabilityRQType;
import com.nequi.uts601.messaging.services.seiya.CheckAvailabilityRSType;
import com.nequi.uts601.messaging.services.seiya.MessageRQ;
import com.nequi.uts601.messaging.services.seiya.MessageRS;
import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;

/**
 * Session Bean implementation class COCheckAvailabilityServiceSeiya
 * 
 *
 */
@Stateless(mappedName = Constant.COMMON_STRING_COCHECKAVAILABILITY_SERVICE_SEIYA)
@LocalBean
public class COCheckAvailabilityServiceSeiya extends CallServiceUtil
        implements ServiceBean {
    @PersistenceContext(unitName = Constant.COMMON_STRING_PERSISTENCE_UNIT_NAME)
    private EntityManager em;
    private ServiceRegistry serviceRegistry;

    private GenericLogger logger;
    private ParameterJPAService parameterJPAService;

    @PostConstruct
    void init() {
        logger = LoggerFactory.getLogger(ServiceTypeEnum.BUSSINES,
                COCheckAvailabilityServiceSeiya.class);
        this.parameterJPAService = new ParameterJPAServiceIMPL();
    }

    /**
     * Metodo que implementa de la interfaz {@link ServiceBean}
     * 
     * @see ServiceBean#executeOperation(String)
     */
    @Override
    public String executeOperation(String request) {

        MessageRS messageRS = null;
        MessageRQ messageRQ = null;
        RequestHeaderType requestHeader = null;
        String messageId = Constant.COMMON_STRING_EMPTY;
        String consumerId = Constant.COMMON_STRING_EMPTY;
        CheckAvailabilityRQType checkAvailabilityRQType = null;
        String response = null;
        try {
            serviceRegistry = ServiceRegistry.getInstance();

            messageRQ = (MessageRQ) UtilJSON
                    .parsePayloadWithJaxbAnnotation(request, MessageRQ.class);

            requestHeader = messageRQ.getRequestMessage().getHeader();

            messageId = requestHeader.getMessageID();

            if (null != requestHeader.getConsumer()) {
                consumerId = requestHeader.getConsumer().getId();
            }

            checkAvailabilityRQType = messageRQ.getRequestMessage().getBody()
                    .getCheckAvailabilityRQ();

            // Consultamos parametros de disponibilidad
            List<Parametro> availabilityParameters = parameterJPAService
                    .getRegionParameter(
                            Constant.COMMON_STRING_AVAILABILITY_PARAMETERS_CODE,
                            requestHeader.getDestination().getServiceRegion(),
                            em);

            if (null == availabilityParameters
                    || availabilityParameters.isEmpty()) {
                logger.traceError(Constant.ERROR_MESSAGE_DB_PARAMETER_NOT_FOUND,
                        new MBSException(
                                Constant.ERROR_MESSAGE_DB_PARAMETER_NOT_FOUND),
                        messageId, consumerId);

                messageRS = CommonUtil.responseErrorMessage(requestHeader,
                        Constant.ERROR_CODE_PARAMETER_NOT_FOUND,
                        Constant.DB_SYSTEM, Constant.APP_SYSTEM, logger,
                        serviceRegistry);

                return UtilJSON.parseJSONToString(messageRS);
            }

            Parametro availability = CommonUtil.getParameterByName(
                    availabilityParameters,
                    checkAvailabilityRQType.getOperation());

            if (null == availability) {
                logger.traceError(Constant.ERROR_MESSAGE_DB_PARAMETER_NOT_FOUND,
                        new MBSException(
                                Constant.ERROR_MESSAGE_DB_PARAMETER_NOT_FOUND),
                        messageId, consumerId);

                messageRS = CommonUtil.responseErrorMessage(requestHeader,
                        Constant.ERROR_CODE_PARAMETER_NOT_FOUND,
                        Constant.DB_SYSTEM, Constant.APP_SYSTEM, logger,
                        serviceRegistry);

                return UtilJSON.parseJSONToString(messageRS);
            }

            // Obtenemos fecha de inicio y de fin
            List<ParametroAtributo> attributes = availability
                    .getParametroAtributos();
            String initialDate = CommonUtil.getAttribute(attributes,
                    Constant.COMMON_STRING_INITIAL_HOUR_ATTRIBUTE);
            String finalDate = CommonUtil.getAttribute(attributes,
                    Constant.COMMON_STRING_FINAL_HOUR_ATTRIBUTE);

            Boolean isAvailability = CommonUtil.checkAvailability(initialDate,
                    finalDate);

            if (isAvailability) {
                messageRS = CommonUtil.getResponseStructure(
                        messageRQ.getRequestMessage().getHeader(),
                        Constant.COMMON_STRING_SUCCESS_CODE,
                        Constant.COMMON_STRING_SUCCESS_MAYUS);
            } else {
                messageRS = CommonUtil.responseErrorMessage(requestHeader,
                        Constant.ERROR_CODE_AVAILABILTY, Constant.SYS_MBS,
                        Constant.APP_SYSTEM, logger, serviceRegistry);
            }

        } catch (JPAException e) {

            logger.traceError(Constant.ERROR_DB_OPERATION, e, messageId,
                    consumerId);
            messageRS = CommonUtil.responseErrorMessage(requestHeader,
                    Constant.ERROR_DEFAULT_CODE, Constant.DB_SYSTEM,
                    Constant.APP_SYSTEM, logger, serviceRegistry);

        } catch (CommonUtilException e) {

            logger.traceError(Constant.ERROR_MESSAGE_PARSING_ERROR, e,
                    messageId, consumerId);
            messageRS = CommonUtil.responseErrorMessage(requestHeader,
                    Constant.ERROR_PARSE, Constant.MDW_SYSTEM,
                    Constant.APP_SYSTEM, logger, serviceRegistry);

        } catch (RestClientException e) {
            logger.traceError(Constant.ERROR_TO_CALL_REST, e, messageId,
                    consumerId);
            messageRS = CommonUtil.responseErrorMessage(requestHeader,
                    Constant.ERROR_CODE_REST_CONSUMER_ERROR, Constant.SYS_MBS,
                    Constant.APP_SYSTEM, logger, serviceRegistry);
        }
        CheckAvailabilityRSType checkAvailabilityRSType = new CheckAvailabilityRSType();
        messageRS.getResponseMessage().getBody()
                .setCheckAvailabilityRS(checkAvailabilityRSType);
        response = CommonUtil.generateResponseAsString(messageRS, logger,
                messageId, consumerId);
        return response;
    }

}
