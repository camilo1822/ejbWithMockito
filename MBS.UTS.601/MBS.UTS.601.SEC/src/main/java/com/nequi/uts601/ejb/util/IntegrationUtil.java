/**
 * 
 */
package com.nequi.uts601.ejb.util;

import java.util.ArrayList;
import java.util.List;

import com.nequi.cmm.consumer.exception.CommonUtilException;
import com.nequi.cmm.consumer.exception.RestClientException;
import com.nequi.cmm.consumer.pojo.ServicePojo;
import com.nequi.cmm.consumer.registry.ServiceRegistry;
import com.nequi.cmm.consumer.rest.util.GenericRestClient;
import com.nequi.cmm.consumer.util.CommonServiceConsumerUtil;
import com.nequi.cmm.consumer.util.UtilJSON;
import com.nequi.mdw.common.tracerv7.service.GenericLogger;
import com.nequi.uts601.ejb.exception.MBSException;
import com.nequi.uts601.messaging.services.integration.ContentType;
import com.nequi.uts601.messaging.services.integration.EmailGeneralRequestType;
import com.nequi.uts601.messaging.services.integration.IntegrationRQ;
import com.nequi.uts601.messaging.services.integration.IntegrationRS;
import com.nequi.uts601.messaging.services.integration.IntegrationRequestType;
import com.nequi.uts601.messaging.services.integration.ServiceType;

/**
 * @author juan.arboleda
 * 
 *         Clase utilitaria
 *
 */
public class IntegrationUtil {

    /**
     * Constructor Privado
     */
    private IntegrationUtil() {

    }

    /**
     * Metodo para construir request de integracion
     * 
     * @param header
     * @param serviceRegion
     * @param serviceName
     * @param serviceOperation
     * @param serviceVersion
     * @return {@code IntegrationRQ}
     */
    public static IntegrationRQ getIntegrationRQ(
            com.nequi.uts601.messaging.services.seiya.RequestHeaderType header,
            String serviceRegion, String serviceName, String serviceOperation,
            String serviceVersion) {

        IntegrationRQ integrationRQ = new IntegrationRQ();
        IntegrationRequestType integrationRequestType = new IntegrationRequestType();
        com.nequi.uts601.messaging.services.integration.RequestHeaderType requestHeaderType = new com.nequi.uts601.messaging.services.integration.RequestHeaderType();

        requestHeaderType.setChannel(Constant.COMMON_STRING_SERVICE_ID);
        requestHeaderType.setMessageId(null != header.getMessageID()
                ? header.getMessageID() : Constant.COMMON_STRING_EMPTY);

        // Consumer
        com.nequi.uts601.messaging.services.integration.ConsumerType consumerType = new com.nequi.uts601.messaging.services.integration.ConsumerType();
        consumerType.setId(Constant.COMMON_STRING_SERVICE_ID);
        consumerType.setDescription(Constant.COMMON_STRING_SERVICE_NAME);

        // Destination
        com.nequi.uts601.messaging.services.integration.DestinationType destinationType = new com.nequi.uts601.messaging.services.integration.DestinationType();
        com.nequi.uts601.messaging.services.integration.ContainerType containerType = new com.nequi.uts601.messaging.services.integration.ContainerType();
        containerType.setId(Constant.COMMON_STRING_CONTAINER_TYPE_ID);
        containerType.setName(Constant.COMMON_STRING_CONTAINER_TYPE_NAME);
        destinationType.setContainer(containerType);
        destinationType.setRegion(serviceRegion);
        ServiceType serviceType = new ServiceType();
        serviceType.setName(serviceName);
        serviceType.setOperation(serviceOperation);
        serviceType.setVersion(serviceVersion);
        destinationType.setService(serviceType);
        requestHeaderType.setDestination(destinationType);

        requestHeaderType.setRequestDateTime(CommonServiceConsumerUtil
                .getCurrentTimeStamp(Constant.COMMON_FORMAT_DATE_TO_FRONT));

        // Header
        integrationRequestType.setHeader(requestHeaderType);

        // Body
        com.nequi.uts601.messaging.services.integration.RequestBodyType requestBodyType = new com.nequi.uts601.messaging.services.integration.RequestBodyType();
        integrationRequestType.setBody(requestBodyType);

        integrationRQ.setIntegrationRequest(integrationRequestType);

        return integrationRQ;
    }

    /**
     * Metodo para validar respuesta
     * 
     * @param integrationRS
     * @return {@code boolean}
     */
    public static boolean successResponse(IntegrationRS integrationRS) {

        return null != integrationRS
                && null != integrationRS.getIntegrationResponse()
                && null != integrationRS.getIntegrationResponse().getHeader()
                && Constant.COMMON_STRING_SUCCESS_CODE
                        .equals(integrationRS.getIntegrationResponse()
                                .getHeader().getStatus().getCode());

    }

    /**
     * Metodo para consumir operaciones de integracion
     * 
     * @param request
     * @param serviceRegistry
     * @param operation
     * @param requestBodyObjectType
     * @param logger
     * @return {@code IntegrationRS}
     * @throws RestClientException
     * @throws CommonUtilException
     */
    public static IntegrationRS callIntegrationService(
            com.nequi.uts601.messaging.services.seiya.MessageRQ request,
            ServiceRegistry serviceRegistry, String operation,
            String serviceName, String version,
            com.nequi.uts601.messaging.services.integration.RequestBodyType requestBodyObjectType,
            GenericLogger logger)
            throws CommonUtilException, RestClientException {

        ServicePojo servicePojo = RegistryUtil.getServiceRegistryPojo(
                request.getRequestMessage().getHeader().getMessageID(),
                serviceName, operation, Constant.INTEGRATION_CLASIFICATION,
                Constant.COMMON_STRING_REGION_CORE,
                Constant.COMMON_STRING_DEFAULT_VERSION, serviceRegistry);

        String messageId = request.getRequestMessage().getHeader()
                .getMessageID();
        String consumerId = null != request.getRequestMessage().getHeader()
                .getConsumer()
                        ? request.getRequestMessage().getHeader().getConsumer()
                                .getId()
                        : Constant.COMMON_STRING_EMPTY;
        if (null == servicePojo) {
            logger.traceError(Constant.ERROR_MESSAGE_DB_QUERY_NO_RESULTS,
                    new MBSException(
                            Constant.ERROR_MESSAGE_DB_QUERY_NO_RESULTS),
                    messageId, consumerId);

            return null;

        }

        IntegrationRQ integrationRQ = getIntegrationRQ(
                request.getRequestMessage().getHeader(),
                request.getRequestMessage().getHeader().getDestination()
                        .getServiceRegion(),
                serviceName, operation, version);

        if (null != operation) {
            integrationRQ.getIntegrationRequest().getHeader().getDestination()
                    .getService().setOperation(operation);
        }

        integrationRQ.getIntegrationRequest().setBody(requestBodyObjectType);

        String response = GenericRestClient.sendRequest(integrationRQ,
                String.class, servicePojo.getEndPoint(),
                servicePojo.getAuthBasicUser(), servicePojo.getAuthBasicPwd(),
                servicePojo.getConnectTimeOut(), servicePojo.getReadTimeOut());

        IntegrationRS integrationRS = UtilJSON
                .parsePayloadWithJaxbAnnotation(response, IntegrationRS.class);

        if (!successResponse(integrationRS)) {

            String description = null != integrationRS
                    && null != integrationRS.getIntegrationResponse()
                            .getHeader().getStatus().getDescription()
                                    ? integrationRS.getIntegrationResponse()
                                            .getHeader().getStatus()
                                            .getDescription()
                                    : Constant.ERROR_TO_CALL_REST;

            logger.traceError(description, new MBSException(description),
                    messageId, consumerId);

        }
        return integrationRS;
    }

    /**
     * Metodo que crea objeto {@code EmailGeneralRequestType}
     * 
     * @param bodyId
     * @param from
     * @param fileType
     * @param files
     * @param content
     * @param names
     * @param to
     * @return {@code EmailGeneralRequestType}
     */
    public static EmailGeneralRequestType getEmailGeneralRequestType(
            String bodyId, String from, String fileType, List<String> files,
            List<ContentType> content, List<String> names, List<String> to) {
        EmailGeneralRequestType emailGeneralRequestType = new EmailGeneralRequestType();
        emailGeneralRequestType.setBodyId(bodyId);
        emailGeneralRequestType.setFrom(from);
        emailGeneralRequestType.setFileType(fileType);
        emailGeneralRequestType.getFile().addAll(files);
        if (null != content) {
            emailGeneralRequestType.getContent().addAll(content);
        }
        emailGeneralRequestType.getName().addAll(names);
        emailGeneralRequestType.getTo().addAll(to);
        return emailGeneralRequestType;
    }

    /**
     * Metodo que arma objeto {@link List<ContentType>} a partir de {@link List
     * <com.nequi.uts601.messaging.services.seiya.ContentType>}
     * 
     * @param contents
     * @return {@link List<ContentType>}
     */
    public static List<ContentType> getListContentType(
            List<com.nequi.uts601.messaging.services.seiya.ContentType> contents) {

        List<ContentType> contentTypes = new ArrayList<>();
        ContentType contentType;
        for (com.nequi.uts601.messaging.services.seiya.ContentType content : contents) {
            contentType = new ContentType();
            contentType.setKey(content.getKey());
            contentType.setValue(content.getValue());
            contentTypes.add(contentType);
        }
        return contentTypes;
    }

}
