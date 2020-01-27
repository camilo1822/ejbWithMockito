package com.nequi.uts601.ejb.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;
import com.ibm.xml.crypto.util.Base64;
import com.nequi.cmm.consumer.exception.CommonUtilException;
import com.nequi.cmm.consumer.registry.ServiceRegistry;
import com.nequi.cmm.consumer.util.CommonServiceConsumerUtil;
import com.nequi.cmm.consumer.util.UtilJSON;
import com.nequi.mdw.common.jpa.exception.JPAException;
import com.nequi.mdw.common.jpa.model.entities.Parametro;
import com.nequi.mdw.common.jpa.model.entities.ParametroAtributo;
import com.nequi.mdw.common.jpa.service.ParameterJPAService;
import com.nequi.mdw.common.tracerv7.service.GenericLogger;
import com.nequi.uts601.messaging.services.seiya.AddressType;
import com.nequi.uts601.messaging.services.seiya.ChannelType;
import com.nequi.uts601.messaging.services.seiya.ConsumerType;
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
public class CommonUtil {

    private CommonUtil() {
    }

    /**
     * Genera un mensaje de Response a Front Homologando el error que se le
     * envie.
     * 
     * @param requestHeader
     * @param errorCode
     * @param system
     * @param destinationSystem
     * @param logger
     * @param serviceRegistry
     * @return <code>MessageRS</code>>
     */
    public static MessageRS responseErrorMessage(
            RequestHeaderType requestHeader, String errorCode, String system,
            String destinationSystem, GenericLogger logger,
            ServiceRegistry serviceRegistry) {

        if (null == requestHeader) {
            requestHeader = new RequestHeaderType();
            requestHeader.setDestination(new DestinationType());
            requestHeader.setChannel(new ChannelType());
            requestHeader.setContainer(new ContainerType());
            AddressType addressType = new AddressType();
            ConsumerType consumerType = new ConsumerType();
            consumerType.setAddress(addressType);
            requestHeader.setConsumer(consumerType);

            requestHeader.setMessageID(String.valueOf((new Date()).getTime()));
        } else if (null != requestHeader.getMessageID()
                && !Constant.COMMON_STRING_EMPTY
                        .equals(requestHeader.getMessageID())) {
            requestHeader.setMessageID(requestHeader.getMessageID());
        }
        requestHeader.getContainer()
                .setId(Constant.COMMON_STRING_CONTAINER_TYPE_ID);
        requestHeader.getContainer()
                .setName(Constant.COMMON_STRING_CONTAINER_TYPE_NAME);
        requestHeader.getChannel().setId(Constant.COMMON_STRING_SERVICE_NAME);
        requestHeader.getChannel().setName(Constant.CHANNEL_APP);

        return buildResponseErrorMessage(requestHeader, errorCode, system,
                destinationSystem, logger, serviceRegistry);
    }

    /**
     * Metodo que construye el mensaje tomando el sistema destino para
     * homologar.
     * 
     * @param requestHeader
     * @param errorCode
     * @param system
     * @param destinationSystem
     * @param logger
     * @param serviceRegistry
     * @return {@link MessageRS}
     */
    private static MessageRS buildResponseErrorMessage(
            RequestHeaderType requestHeader, String errorCode, String system,
            String destinationSystem, GenericLogger logger,
            ServiceRegistry serviceRegistry) {

        ResponseMessageType responseMessageType = new ResponseMessageType();
        ResponseHeaderType responseHeader = new ResponseHeaderType();
        ResponseBodyType responseBody = new ResponseBodyType();
        MessageRS response = new MessageRS();
        ProviderType providerType = new ProviderType();
        StatusType statusType;
        Date responseDate = new Date();

        if (null != requestHeader) {
            responseHeader.setMessageID(requestHeader.getMessageID());
        } else {
            responseHeader.setMessageID(String.valueOf(responseDate.getTime()));
        }

        providerType.setId(Constant.COMMON_STRING_SERVICE_ID);
        providerType.setName(Constant.COMMON_STRING_SERVICE_NAME);

        statusType = new StatusType();

        if (Constant.COMMON_STRING_SUCCESS_CODE.equals(errorCode)) {

            statusType.setCode(Constant.COMMON_STRING_SUCCESS_CODE);
            statusType.setDescription(Constant.COMMON_STRING_SUCCESS_MAYUS);

        } else {

            return HomologateUtil.homologateError(requestHeader, errorCode,
                    system, destinationSystem, requestHeader, logger,
                    serviceRegistry);

        }

        responseHeader.setProvider(providerType);
        responseHeader.setResponseDate(CommonServiceConsumerUtil
                .getCurrentTimeStamp(Constant.COMMON_FORMAT_DATE_TO_FRONT));
        responseHeader.setChannel(null != requestHeader
                ? requestHeader.getChannel().getName() : null);
        responseHeader.setContainer(
                null != requestHeader ? requestHeader.getContainer() : null);
        responseHeader.setDestination(
                null != requestHeader ? requestHeader.getDestination() : null);
        responseHeader.setStatus(statusType);
        responseHeader.setConsumer(
                null != requestHeader ? requestHeader.getConsumer() : null);
        responseMessageType.setHeader(responseHeader);
        responseMessageType.setBody(responseBody);
        response.setResponseMessage(responseMessageType);

        return response;
    }

    /**
     * Metodo que genera una respuesta con el codigo y mensaje especificado
     * 
     * @param headerRQ
     * @param code
     * @param message
     * @return {@link MessageRS}
     */
    public static MessageRS getResponseStructure(RequestHeaderType headerRQ,
            String code, String message) {
        MessageRS response = null;
        response = getResponseStructure();
        ResponseHeaderType headerRS = response.getResponseMessage().getHeader();
        headerRS = setRequestDataToResponse(headerRQ, headerRS);
        response.getResponseMessage().setHeader(headerRS);
        response.getResponseMessage().getHeader().getStatus().setCode(code);
        response.getResponseMessage().getHeader().getStatus()
                .setDescription(message);
        return response;
    }

    /**
     * Metodo que construye la mensajeria de la respuesta de los servicios sin
     * configuracion.
     * 
     * @return {@link MessageRS} generacion de respuesta sin configuracion.
     */
    public static MessageRS getResponseStructure() {

        MessageRS messageRS = new MessageRS();
        ResponseMessageType responseMessageType = new ResponseMessageType();
        ResponseHeaderType responseHeaderType = new ResponseHeaderType();
        DestinationType destinationType = new DestinationType();
        StatusType statusType = new StatusType();
        ProviderType providerType = new ProviderType();
        ContainerType containerType = new ContainerType();
        ConsumerType consumerType = new ConsumerType();
        AddressType addressType = new AddressType();
        consumerType.setAddress(addressType);

        responseHeaderType.setDestination(destinationType);
        responseHeaderType.setStatus(statusType);
        responseHeaderType.setProvider(providerType);
        responseHeaderType.setContainer(containerType);
        responseHeaderType.setConsumer(consumerType);

        ResponseBodyType responseBodyType = new ResponseBodyType();

        responseMessageType.setHeader(responseHeaderType);
        responseMessageType.setBody(responseBodyType);
        messageRS.setResponseMessage(responseMessageType);

        return messageRS;
    }

    /**
     * Metodo que construye el header para la respuesta del servicio.
     * 
     * @param headerRQ
     * @param headerRS
     * @return {@link ResponseHeaderType} header para respuesta del servicio.
     */
    public static ResponseHeaderType setRequestDataToResponse(
            RequestHeaderType headerRQ, ResponseHeaderType headerRS) {
        Date responseDate = new Date();
        String date = CommonServiceConsumerUtil
                .getCurrentTimeStamp(Constant.COMMON_FORMAT_DATE_TO_FRONT);

        if (null == headerRQ) {

            headerRS.getDestination()
                    .setServiceName(Constant.COMMON_STRING_SERVICE_NAME);
            headerRS.getDestination()
                    .setServiceOperation(Constant.COMMON_STRING_EMPTY);
            headerRS.getDestination()
                    .setServiceRegion(Constant.COMMON_STRING_EMPTY);
            headerRS.getDestination()
                    .setServiceVersion(Constant.COMMON_STRING_EMPTY);

            headerRS.setMessageID(String.valueOf(responseDate.getTime()));
            headerRS.getContainer()
                    .setId(Constant.COMMON_STRING_CONTAINER_TYPE_ID);
            headerRS.getContainer()
                    .setName(Constant.COMMON_STRING_CONTAINER_TYPE_NAME);
            headerRS.getConsumer().getAddress()
                    .setDeviceAddress(Constant.COMMON_STRING_EMPTY);
            headerRS.getConsumer().getAddress()
                    .setNetworkAddress(Constant.COMMON_STRING_EMPTY);
            headerRS.getConsumer().setId(Constant.COMMON_STRING_EMPTY);
            headerRS.getConsumer().setName(Constant.COMMON_STRING_EMPTY);
            headerRS.setChannel(Constant.COMMON_STRING_CHANNEL_ID);

        } else {
            headerRS.setDestination(headerRQ.getDestination());
            headerRS.setMessageID(headerRQ.getMessageID());
            headerRS.setContainer(headerRQ.getContainer());
            headerRS.setChannel(headerRQ.getChannel().getId());
            headerRS.setConsumer(headerRQ.getConsumer());
        }

        headerRS.setResponseDate(date);
        headerRS.getProvider().setId(Constant.COMMON_STRING_SERVICE_ID);
        headerRS.getProvider().setName(Constant.COMMON_STRING_SERVICE_NAME);
        return headerRS;
    }

    /**
     * Metodo que parsea la respuesta para generarla en formato JSON.
     * 
     * @param messageRS
     * @param logger
     * @param messageId
     * @param consumerId
     * @return String
     */
    public static String generateResponseAsString(MessageRS messageRS,
            GenericLogger logger, String messageId, String consumerId) {
        try {

            return UtilJSON.parseJSONToString(messageRS);

        } catch (CommonUtilException e1) {
            logger.traceError(Constant.ERROR_MESSAGE_PARSING_ERROR, e1,
                    messageId, consumerId);
        }
        return Constant.COMMON_STRING_EMPTY;
    }

    /**
     * <p>
     * Metodo que devuelve parametro por Nombre.
     * </p>
     * 
     * @param parameters
     * @param name
     * @return Parametro
     */
    public static Parametro getParameterByName(List<Parametro> parameters,
            String name) {
        Parametro parametro = null;
        if (null != parameters) {
            parametro = parameters.stream()
                    .filter(p -> p.getNombre().equalsIgnoreCase(name)).findAny()
                    .orElse(null);
        }
        return parametro;
    }

    /**
     * Metodo que calcula si la hora actual esta entre un rango dado de horas
     * 
     * @param initialDate
     * @param finalDate
     * @return Boolean
     */
    public static Boolean checkAvailability(String initialDate,
            String finalDate) {
        String[] partsInitialDate = initialDate
                .split(Constant.COMMON_STRING_COLON);
        Calendar intialCalendar = Calendar.getInstance();
        intialCalendar.set(Calendar.HOUR_OF_DAY,
                Integer.parseInt(partsInitialDate[Constant.COMMON_INT_ZERO]));
        intialCalendar.set(Calendar.MINUTE,
                Integer.parseInt(partsInitialDate[Constant.COMMON_INT_ONE]));
        intialCalendar.set(Calendar.SECOND,
                Integer.parseInt(partsInitialDate[Constant.COMMON_INT_TWO]));

        String[] partsFinalDate = finalDate.split(Constant.COMMON_STRING_COLON);
        Calendar finalCalendar = Calendar.getInstance();
        finalCalendar.set(Calendar.HOUR_OF_DAY,
                Integer.parseInt(partsFinalDate[Constant.COMMON_INT_ZERO]));
        finalCalendar.set(Calendar.MINUTE,
                Integer.parseInt(partsFinalDate[Constant.COMMON_INT_ONE]));
        finalCalendar.set(Calendar.SECOND,
                Integer.parseInt(partsFinalDate[Constant.COMMON_INT_TWO]));

        Calendar calendar = Calendar.getInstance();

        if (finalCalendar.before(intialCalendar)
                && calendar.after(intialCalendar)) {
            finalCalendar.add(Calendar.DATE, Constant.COMMON_INT_ONE);
        }

        if (finalCalendar.before(intialCalendar)
                && calendar.before(intialCalendar)) {
            intialCalendar.add(Calendar.DATE, -Constant.COMMON_INT_ONE);
        }
        return calendar.before(finalCalendar) && calendar.after(intialCalendar);
    }

    /**
     * Metodo que retorna el valor de un atributo por su nombre
     * 
     * @param attributes
     * @param attributeName
     * @return getAttribute
     */
    public static String getAttribute(List<ParametroAtributo> attributes,
            String attributeName) {
        for (ParametroAtributo attribute : attributes) {
            if (attributeName.equals(attribute.getAtributo().getNombre())) {
                return attribute.getValor();
            }
        }
        return Constant.COMMON_STRING_EMPTY;
    }

    /**
     * Metodo que devuelve la fecha actual en espanol
     * 
     * @param dateFormat
     * @return Timestamp Actual
     */
    public static String getCurrentTimeStampSpanish(String dateFormat) {

        Date curDate;
        SimpleDateFormat format;

        curDate = new Date();
        format = new SimpleDateFormat(dateFormat,
                new Locale(Constant.CONSTANT_LANGUAGE_SPANISH_LOWER_CASE,
                        Constant.CONSTANT_LANGUAGE_SPANISH_UPPER_CASE));

        return format.format(curDate);
    }

    /**
     * Método para establecer conexión con Amazon, con rol o permisos desde el
     * servidor
     * 
     * @param logger
     * @param messageId
     * @param consumerId
     * @return <code>AWSKMS</code>
     * @throws BackoutFIS502Exception
     */
    public static AWSKMS getAWSKMSClient(GenericLogger logger, String messageId,
            String consumerId) {
        try {

            return AWSKMSClientBuilder.standard().withRegion(Regions.US_EAST_1)
                    .withCredentials(
                            InstanceProfileCredentialsProvider.getInstance())
                    .build();

        } catch (Exception e) {
            logger.traceError(Constant.ERROR_AMAZON_CONNECTION, e, messageId,
                    consumerId);
        }
        return null;
    }

    /**
     * Metodo para descifrar un texto con SDK de amazon (KMS).
     * 
     * @param cipherString
     * @param awsKms
     * @param messageId
     * @param consumerId
     * @param logger
     * 
     * @return {@link String}
     * @throws BackoutFIS502Exception
     */
    public static String getKMSdecrypt(String cipherString, AWSKMS awsKms,
            String messageId, String consumerId, GenericLogger logger) {

        try {
            // Create decode request and decode
            byte[] cipherBytes = Base64.decode(cipherString);

            ByteBuffer cipherBuffer = ByteBuffer.wrap(cipherBytes);

            DecryptRequest decryptRequest = new DecryptRequest()
                    .withCiphertextBlob(cipherBuffer);

            DecryptResult decryptResult = awsKms.decrypt(decryptRequest);

            // Convert the response plaintext bytes to a string
            return new String(decryptResult.getPlaintext().array(),
                    StandardCharsets.UTF_8);

        } catch (Exception e) {
            logger.traceError(Constant.ERROR_AMAZON_KMS_DECRYPT, e, messageId,
                    consumerId);
        }
        return null;
    }

    public static List<Parametro> getParameterList(
            RequestHeaderType requestHeader, EntityManager em,
            ParameterJPAService parameterJPAService) throws JPAException {
        return parameterJPAService.getRegionParameter(
                Constant.COMMON_STRING_AVAILABILITY_PARAMETERS_CODE,
                requestHeader.getDestination().getServiceRegion(), em);
    }
}