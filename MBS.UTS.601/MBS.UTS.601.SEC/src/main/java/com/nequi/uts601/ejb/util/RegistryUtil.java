/**
 * 
 */
package com.nequi.uts601.ejb.util;

import java.util.Date;

import com.nequi.cmm.consumer.exception.CommonUtilException;
import com.nequi.cmm.consumer.exception.RestClientException;
import com.nequi.cmm.consumer.pojo.ServicePojo;
import com.nequi.cmm.consumer.registry.ServiceRegistry;
import com.nequi.cmm.consumer.rest.util.GenericRestClient;
import com.nequi.cmm.consumer.util.CommonServiceConsumerUtil;
import com.nequi.cmm.consumer.util.UtilJSON;
import com.nequi.cmm.messaging.registry.BodyRequestType;
import com.nequi.cmm.messaging.registry.ConsumerType;
import com.nequi.cmm.messaging.registry.ContainerType;
import com.nequi.cmm.messaging.registry.EndpointType;
import com.nequi.cmm.messaging.registry.HeaderRequestType;
import com.nequi.cmm.messaging.registry.LookupRequestType;
import com.nequi.cmm.messaging.registry.LookupResponseType;
import com.nequi.cmm.messaging.registry.RegistryRQ;
import com.nequi.cmm.messaging.registry.RegistryRS;
import com.nequi.cmm.messaging.registry.RegistryRequestType;
import com.nequi.cmm.messaging.registry.TimeoutType;

/**
 * 
 * Clase utilitaria
 *
 */
public class RegistryUtil {

    /**
     * 
     */
    private RegistryUtil() {

    }

    /**
     * Método que construye un <b>RegistryRQ</b> a partir de un
     * <b>RequestHeaderType</b>
     * 
     * @param messageId
     * @param serviceName
     * @param serviceOperation
     * @param classification
     * @param region
     * @param version
     * @return<code>RegistryRQ</code>
     */
    private static RegistryRQ getRegistryRQ(String messageId,
            String serviceName, String serviceOperation, String classification,
            String region, String version) {

        RegistryRQ registryRQ = new RegistryRQ();
        RegistryRequestType registryRequestType = new RegistryRequestType();
        HeaderRequestType headerRequestType = new HeaderRequestType();
        ConsumerType consumerType = new ConsumerType();
        ContainerType containerType = new ContainerType();
        BodyRequestType bodyRequestType = new BodyRequestType();
        LookupRequestType lookupRequestType = new LookupRequestType();

        registryRQ.setRegistryRequest(registryRequestType);
        registryRequestType.setHeader(headerRequestType);

        headerRequestType.setMessageId(null != messageId ? messageId
                : String.valueOf(new Date().getTime()));
        headerRequestType.setMessageDate(CommonServiceConsumerUtil
                .getCurrentTimeStamp(Constant.COMMON_FORMAT_DATE_TO_FRONT));

        headerRequestType.setType(Constant.COMMON_STRING_EMPTY);
        headerRequestType.setOperation(Constant.COMMON_STRING_EMPTY);

        headerRequestType.setConsumer(consumerType);

        /* Atibutos del consumer */
        consumerType.setId(Constant.COMMON_STRING_SERVICE_ID);
        consumerType.setName(Constant.COMMON_STRING_SERVICE_NAME);

        consumerType.setContainer(containerType);
        containerType.setId(Constant.COMMON_STRING_CONTAINER_TYPE_ID);
        containerType.setName(Constant.COMMON_STRING_CONTAINER_TYPE_NAME);

        /* Atributos del body */
        registryRequestType.setBody(bodyRequestType);

        bodyRequestType.setLookupRequest(lookupRequestType);

        lookupRequestType.setName(serviceName);
        lookupRequestType.setOperation(serviceOperation);
        lookupRequestType.setClassification(classification);
        lookupRequestType.setRegion(region);
        lookupRequestType.setVersion(version);

        return registryRQ;
    }

    /**
     * Método que consulta al serviceRegistry para resolver el endpoint del
     * servicio que se solicita
     * 
     * @param messageId
     * @param serviceName
     * @param serviceOperation
     * @param classification
     * @param region
     * @param version
     * @param serviceRegistry
     * @return <code>ServicePojo</code>
     * @throws CommonUtilException
     * @throws RestClientException
     */
    public static ServicePojo getServiceRegistryPojo(String messageId,
            String serviceName, String serviceOperation, String classification,
            String region, String version, ServiceRegistry serviceRegistry)
            throws CommonUtilException, RestClientException {
        ServicePojo servicePojo = new ServicePojo();
        RegistryRQ registryRQ = RegistryUtil.getRegistryRQ(messageId,
                serviceName, serviceOperation, classification, region, version);

        String registryRString = serviceRegistry.lookUpRegistryService(
                registryRQ, RegistryRS.class, serviceName, serviceOperation,
                classification, region, version);

        RegistryRS registryRS = UtilJSON.parsePayloadWithJaxbAnnotation(
                registryRString, RegistryRS.class);

        LookupResponseType lookupResponseType = registryRS.getRegistryResponse()
                .getBody().getLookupResponse();

        EndpointType endpointType = lookupResponseType.getEndpoint();
        TimeoutType timeout = lookupResponseType.getTimeout();

        String endPoint = GenericRestClient.getUrlRestServicesEndPoint(
                endpointType.getProtocol(), endpointType.getHost(),
                endpointType.getPort(), endpointType.getContext());

        Integer connectTimeOut = Integer.parseInt(timeout.getConnection());
        Integer readTimeOut = Integer.parseInt(timeout.getRead());

        servicePojo.setEndPoint(endPoint);
        servicePojo.setAuthBasicUser(endpointType.getAuthBasicUser());
        servicePojo.setAuthBasicPwd(endpointType.getAuthBasicPwd());
        servicePojo.setConnectTimeOut(connectTimeOut);
        servicePojo.setReadTimeOut(readTimeOut);

        return servicePojo;
    }

}
