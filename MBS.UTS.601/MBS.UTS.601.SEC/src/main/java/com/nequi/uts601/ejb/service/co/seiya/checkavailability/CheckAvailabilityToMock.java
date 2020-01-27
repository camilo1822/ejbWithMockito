package com.nequi.uts601.ejb.service.co.seiya.checkavailability;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import com.nequi.cmm.consumer.exception.CommonUtilException;
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
import com.nequi.uts601.ejb.service.IGeneral;
import com.nequi.uts601.ejb.util.CommonUtil;
import com.nequi.uts601.ejb.util.Constant;
import com.nequi.uts601.messaging.services.seiya.CheckAvailabilityRQType;
import com.nequi.uts601.messaging.services.seiya.CheckAvailabilityRSType;
import com.nequi.uts601.messaging.services.seiya.MessageRQ;
import com.nequi.uts601.messaging.services.seiya.MessageRS;
import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;

@Stateless(mappedName = "CheckAvailabilityToMock")
@LocalBean
public class CheckAvailabilityToMock extends CallServiceUtil
        implements IGeneral {

    private GenericLogger logger;
    private ParameterJPAService parameterJPAService;

    @PostConstruct
    void init() {
        logger = LoggerFactory.getLogger(ServiceTypeEnum.BUSSINES,
                COCheckAvailabilityServiceSeiya.class);
        this.parameterJPAService = new ParameterJPAServiceIMPL();
    }

    @Override
    public String executeOperation(String request,
            ServiceRegistry serviceRegistry, EntityManager em)
            throws MBSException {

        MessageRS messageRS = null;
        MessageRQ messageRQ = null;
        RequestHeaderType requestHeader = null;
        String messageId = Constant.COMMON_STRING_EMPTY;
        String consumerId = Constant.COMMON_STRING_EMPTY;
        CheckAvailabilityRQType checkAvailabilityRQType = null;
        String response = null;
        try {

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

        }
        CheckAvailabilityRSType checkAvailabilityRSType = new CheckAvailabilityRSType();
        messageRS.getResponseMessage().getBody()
                .setCheckAvailabilityRS(checkAvailabilityRSType);
        response = CommonUtil.generateResponseAsString(messageRS, logger,
                messageId, consumerId);
        return response;
    }

    // public MessageRS sendEmail(MessageRQ messageRQ, String messageId,
    // String consumerId, ServiceRegistry serviceRegistry)
    // throws CommonUtilException, RestClientException {
    //
    // RequestBodyType requestBodyObjectType = new RequestBodyType();
    // EmailGeneralRequestType emailGeneralRequestType = IntegrationUtil
    // .getEmailGeneralRequestType("M123", "correo@correo.com", "PDF",
    // Arrays.asList("file"), null, Arrays.asList("name"),
    // Arrays.asList("correo@correo.com"));
    // requestBodyObjectType.setEmailGeneralRequest(emailGeneralRequestType);
    //
    // IntegrationRS integrationRS = IntegrationUtil.callIntegrationService(
    // messageRQ, serviceRegistry,
    // Constant.COMMON_STRING_OP_SEND_EMAIL_GENERAL,
    // Constant.COMMON_STRING_OP_GET_SERVICE_EMAIL,
    // Constant.COMMON_STRING_DEFAULT_VERSION, requestBodyObjectType,
    // logger);
    //
    // if (!IntegrationUtil.successResponse(integrationRS)) {
    //
    // logger.traceError(Constant.ERROR_TO_CALL_REST,
    // new MBSException(Constant.ERROR_TO_CALL_REST), messageId,
    // consumerId);
    // return CommonUtil.responseErrorMessage(
    // messageRQ.getRequestMessage().getHeader(),
    // Constant.ERROR_CODE_REST, Constant.MDW_SYSTEM,
    // Constant.APP_SYSTEM, logger, serviceRegistry);
    // }
    //
    // return CommonUtil.getResponseStructure(
    // messageRQ.getRequestMessage().getHeader(),
    // Constant.COMMON_STRING_SUCCESS_CODE,
    // Constant.COMMON_STRING_SUCCESS_MAYUS);
    // }
}
