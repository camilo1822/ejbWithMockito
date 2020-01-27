/**
 * 
 */
package com.nequi.uts601.util;

import java.util.Date;

import com.nequi.cmm.consumer.util.CommonServiceConsumerUtil;
import com.nequi.uts601.ejb.util.Constant;
import com.nequi.uts601.messaging.services.seiya.ContainerType;
import com.nequi.uts601.messaging.services.seiya.DestinationType;
import com.nequi.uts601.messaging.services.seiya.MessageRS;
import com.nequi.uts601.messaging.services.seiya.ProviderType;
import com.nequi.uts601.messaging.services.seiya.RequestHeaderType;
import com.nequi.uts601.messaging.services.seiya.ResponseBodyType;
import com.nequi.uts601.messaging.services.seiya.ResponseHeaderType;
import com.nequi.uts601.messaging.services.seiya.ResponseMessageType;
import com.nequi.uts601.messaging.services.seiya.StatusType;

/**
 *
 */
public class BuildMessageServiceUtil {
    /**
     * Default constructor
     */
    private BuildMessageServiceUtil() {
        super();
    }

    /**
     * Metodo que configura la respuesta con la configuracion dada.
     * 
     * @param headerRQ
     * @param code
     * @param message
     * @return {@link MessageRS}
     */
    public static MessageRS generateResponse(RequestHeaderType headerRQ,
            String code, String message) {

        MessageRS messageRS = getGenericResponseStructure();
        messageRS.getResponseMessage().setHeader(setRequestDataToResponse(
                headerRQ, messageRS.getResponseMessage().getHeader()));
        messageRS.getResponseMessage().getHeader().getStatus().setCode(code);
        messageRS.getResponseMessage().getHeader().getStatus()
                .setDescription(message);
        return messageRS;
    }

    /**
     * Metodo que configura la respuesta.
     * 
     * @param headerRQ
     * @param headerRS
     * @return {@link ResponseHeaderType} header para respuesta de servicio.
     */
    private static ResponseHeaderType setRequestDataToResponse(
            RequestHeaderType headerRQ, ResponseHeaderType headerRS) {
        String responseDate;
        responseDate = CommonServiceConsumerUtil.getCurrentTimeStamp(
                Constant.COMMON_FORMAT_DATE_TO_FRONT);
        if (null == headerRQ) {
            headerRS.getDestination()
                    .setServiceName(Constant.COMMON_STRING_SERVICE_NAME);
            headerRS.getDestination()
                    .setServiceOperation(Constant.COMMON_STRING_EMPTY);
            headerRS.getDestination()
                    .setServiceRegion(Constant.COMMON_STRING_EMPTY);
            headerRS.getDestination()
                    .setServiceVersion(Constant.COMMON_STRING_EMPTY);

            headerRS.setMessageID(String.valueOf(new Date().getTime()));
            headerRS.getContainer()
                    .setId(Constant.COMMON_STRING_CONTAINER_TYPE_ID);
            headerRS.getContainer()
                    .setName(Constant.COMMON_STRING_CONTAINER_TYPE_NAME);

        } else {
            headerRS.setDestination(headerRQ.getDestination());
            headerRS.setMessageID(headerRQ.getMessageID());
            headerRS.setContainer(headerRQ.getContainer());
            headerRS.setChannel(headerRQ.getChannel().getId());
        }

        headerRS.setResponseDate(responseDate);
        headerRS.getProvider().setId(Constant.COMMON_STRING_SERVICE_ID);
        headerRS.getProvider()
                .setName(Constant.COMMON_STRING_SERVICE_NAME);
        return headerRS;
    }

    /**
     * Metodo que genera una respuesta por defecto.
     * 
     * @return {@link MessageRS} respuesta generica.
     */
    private static MessageRS getGenericResponseStructure() {

        MessageRS messageRS = new MessageRS();
        ResponseMessageType responseMessageType = new ResponseMessageType();
        ResponseHeaderType responseHeaderType = new ResponseHeaderType();
        DestinationType destinationType = new DestinationType();
        StatusType statusType = new StatusType();
        ProviderType providerType = new ProviderType();
        ContainerType containerType = new ContainerType();

        responseHeaderType.setDestination(destinationType);
        responseHeaderType.setStatus(statusType);
        responseHeaderType.setProvider(providerType);
        responseHeaderType.setContainer(containerType);

        ResponseBodyType responseBodyType = new ResponseBodyType();

        responseMessageType.setHeader(responseHeaderType);
        responseMessageType.setBody(responseBodyType);
        messageRS.setResponseMessage(responseMessageType);
        return messageRS;
    }

}