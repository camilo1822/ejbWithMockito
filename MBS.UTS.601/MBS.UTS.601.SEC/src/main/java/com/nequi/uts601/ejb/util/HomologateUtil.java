/**
 * 
 */
package com.nequi.uts601.ejb.util;

import java.util.Date;

import com.nequi.cmm.consumer.exception.RestClientException;
import com.nequi.cmm.consumer.pojo.ServicePojo;
import com.nequi.cmm.consumer.registry.ServiceRegistry;
import com.nequi.cmm.consumer.rest.util.GenericRestClient;
import com.nequi.cmm.consumer.util.CommonServiceConsumerUtil;
import com.nequi.cmm.consumer.util.UtilJSON;
import com.nequi.mdw.common.tracerv7.service.GenericLogger;
import com.nequi.uts601.messaging.services.homologator.BodyRequestType;
import com.nequi.uts601.messaging.services.homologator.ConsumerType;
import com.nequi.uts601.messaging.services.homologator.HeaderRequestType;
import com.nequi.uts601.messaging.services.homologator.HmgRequestType;
import com.nequi.uts601.messaging.services.homologator.HmgResponseType;
import com.nequi.uts601.messaging.services.seiya.MessageRS;
import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;
import com.nequi.uts601.messaging.services.seiya.StatusType;

public class HomologateUtil {

    /**
     * 
     */
    private HomologateUtil() {
    }

    /**
     * Método que obtiene el request del homologador a partir de un object
     * requestHeaderType
     * 
     * @param errorCode
     * @param originSystem
     * @param destinationSystem
     * @param requestHeaderType
     * @return
     */
    public static HmgRequestType getRequestHomologator(String errorCode,
            String originSystem, String destinationSystem,
            RequestHeaderType headerPetition) {

        HmgRequestType request = new HmgRequestType();
        HeaderRequestType headerRequestType = new HeaderRequestType();
        Date date = new Date();
        ConsumerType consumerType = new ConsumerType();
        if (null != headerPetition) {
            headerRequestType.setMessageId(headerPetition.getMessageID());
            consumerType.setId(headerPetition.getConsumer().getId());
            consumerType.setDescription(headerPetition.getConsumer().getName());
        } else {
            headerRequestType.setMessageId(String.valueOf(date.getTime()));
            consumerType.setId(Constant.COMMON_STRING_SERVICE_ID);
            consumerType.setDescription(Constant.COMMON_STRING_SERVICE_NAME);
        }
        headerRequestType.setConsumer(consumerType);
        headerRequestType.setMessageDate(CommonServiceConsumerUtil
                .getCurrentTimeStamp(Constant.COMMON_FORMAT_DATE_TO_FRONT));
        request.setHeader(headerRequestType);
        BodyRequestType bodyRequestType = new BodyRequestType();
        bodyRequestType.setErrorCode(errorCode);
        bodyRequestType.setOriginSystem(originSystem);

        if (null == destinationSystem && null != headerPetition) {
            String system = (headerPetition.getChannel().getId()).toLowerCase();
            if (Constant.CHANNELS.containsKey(system)) {
                bodyRequestType
                        .setDestinationSystem(Constant.CHANNELS.get(system));
            }
        }
        bodyRequestType.setDestinationSystem(destinationSystem);
        request.setBody(bodyRequestType);
        return request;
    }

    /**
     * Metodo para homologar errores dependiendo al sistema
     * 
     * @param errorCode
     * @param originSystem
     * @param destinationSystem
     * @param headerRequestType
     * @param logger
     * @param serviceRegistry
     * @return {@link MessageRS}
     */
    public static MessageRS homologateError(RequestHeaderType requestHeader,
            String errorCode, String originSystem, String destinationSystem,
            Object headerRequestTypeObject, GenericLogger logger,
            ServiceRegistry serviceRegistry) {

        HmgRequestType hmgRequestType = null;

        StatusType statusType = new StatusType();
        statusType.setCode(Constant.ERROR_DEFAULT_CODE);
        statusType.setDescription(Constant.ERROR_DEFAULT_MESSAGE);

        RequestHeaderType headerRequestType = null;
        String messageId = null;
        String consumerId = null;

        try {
            messageId = requestHeader.getMessageID();

            if (null != requestHeader.getConsumer()) {
                consumerId = requestHeader.getConsumer().getId();
            }

            headerRequestType = UtilJSON.parseSpecificObject(
                    headerRequestTypeObject, RequestHeaderType.class);

            if (null != headerRequestType) {

                hmgRequestType = getRequestHomologator(errorCode, originSystem,
                        destinationSystem, headerRequestType);

                ServicePojo servicePojo = RegistryUtil.getServiceRegistryPojo(
                        requestHeader.getMessageID(),
                        Constant.HOMOLOGATOR_SERVICE_NAME,
                        Constant.HOMOLOGATOR_SERVICE_OPERATION,
                        Constant.INTEGRATION_CLASIFICATION,
                        Constant.COMMON_STRING_REGION_CORE,
                        Constant.COMMON_STRING_DEFAULT_VERSION,
                        serviceRegistry);

                HmgResponseType hmgResponseType = GenericRestClient.sendRequest(
                        hmgRequestType, HmgResponseType.class,
                        servicePojo.getEndPoint(),
                        servicePojo.getAuthBasicUser(),
                        servicePojo.getAuthBasicPwd(),
                        servicePojo.getConnectTimeOut(),
                        servicePojo.getReadTimeOut());

                String codeResult = hmgResponseType.getHeader().getStatus()
                        .getCode();

                if (Constant.COMMON_STRING_SUCCESS_CODE.equals(codeResult)) {

                    statusType.setCode(hmgResponseType.getBody().getOutCode());
                    statusType.setDescription(
                            hmgResponseType.getBody().getOutMessage());

                }

            }
        } catch (RestClientException e) {
            logger.traceError(Constant.ERROR_REST_CLIENT_HOMOLOGATE_ERROR, e,
                    messageId, consumerId);
        } catch (Exception e) {
            logger.traceError(Constant.ERROR_TO_HOMOLOGATE_ERROR, e, messageId,
                    consumerId);
        }

        return CommonUtil.getResponseStructure(headerRequestType,
                statusType.getCode(), statusType.getDescription());
    }

}
