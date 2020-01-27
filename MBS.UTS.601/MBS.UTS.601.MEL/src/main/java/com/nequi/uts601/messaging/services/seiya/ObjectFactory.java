//
// Este archivo se genera mediante JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Cualquier modificación de este archivo se perderá después de volver a compilar el esquema de origen.
// Generado en: AM.09.25 en 10:24:53 AM COT 
//


package com.nequi.uts601.messaging.services.seiya;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.nequi.uts601.messaging.services.seiya package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendDocumentRS_QNAME = new QName("http://nequi.co/message/bussines/services/senddocument", "sendDocumentRS");
    private final static QName _SendDocumentRQ_QNAME = new QName("http://nequi.co/message/bussines/services/senddocument", "sendDocumentRQ");
    private final static QName _CheckAvailabilityRQ_QNAME = new QName("http://nequi.co/message/bussines/services/checkavailability", "checkAvailabilityRQ");
    private final static QName _ResponseMessage_QNAME = new QName("http://nequi.co/message/bussines/services/", "responseMessage");
    private final static QName _CheckAvailabilityRS_QNAME = new QName("http://nequi.co/message/bussines/services/checkavailability", "checkAvailabilityRS");
    private final static QName _RequestMessage_QNAME = new QName("http://nequi.co/message/bussines/services/", "requestMessage");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.nequi.uts601.messaging.services.seiya
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RequestMessageType }
     * 
     */
    public RequestMessageType createRequestMessageType() {
        return new RequestMessageType();
    }

    /**
     * Create an instance of {@link MessageRS }
     * 
     */
    public MessageRS createMessageRS() {
        return new MessageRS();
    }

    /**
     * Create an instance of {@link ResponseMessageType }
     * 
     */
    public ResponseMessageType createResponseMessageType() {
        return new ResponseMessageType();
    }

    /**
     * Create an instance of {@link MessageRQ }
     * 
     */
    public MessageRQ createMessageRQ() {
        return new MessageRQ();
    }

    /**
     * Create an instance of {@link StatusType }
     * 
     */
    public StatusType createStatusType() {
        return new StatusType();
    }

    /**
     * Create an instance of {@link RequestBodyType }
     * 
     */
    public RequestBodyType createRequestBodyType() {
        return new RequestBodyType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link ContainerType }
     * 
     */
    public ContainerType createContainerType() {
        return new ContainerType();
    }

    /**
     * Create an instance of {@link ResponseBodyType }
     * 
     */
    public ResponseBodyType createResponseBodyType() {
        return new ResponseBodyType();
    }

    /**
     * Create an instance of {@link ChannelType }
     * 
     */
    public ChannelType createChannelType() {
        return new ChannelType();
    }

    /**
     * Create an instance of {@link RequestHeaderType }
     * 
     */
    public RequestHeaderType createRequestHeaderType() {
        return new RequestHeaderType();
    }

    /**
     * Create an instance of {@link ProviderType }
     * 
     */
    public ProviderType createProviderType() {
        return new ProviderType();
    }

    /**
     * Create an instance of {@link ConsumerType }
     * 
     */
    public ConsumerType createConsumerType() {
        return new ConsumerType();
    }

    /**
     * Create an instance of {@link SecurityType }
     * 
     */
    public SecurityType createSecurityType() {
        return new SecurityType();
    }

    /**
     * Create an instance of {@link ResponseHeaderType }
     * 
     */
    public ResponseHeaderType createResponseHeaderType() {
        return new ResponseHeaderType();
    }

    /**
     * Create an instance of {@link DestinationType }
     * 
     */
    public DestinationType createDestinationType() {
        return new DestinationType();
    }

    /**
     * Create an instance of {@link CheckAvailabilityRQType }
     * 
     */
    public CheckAvailabilityRQType createCheckAvailabilityRQType() {
        return new CheckAvailabilityRQType();
    }

    /**
     * Create an instance of {@link CheckAvailabilityRSType }
     * 
     */
    public CheckAvailabilityRSType createCheckAvailabilityRSType() {
        return new CheckAvailabilityRSType();
    }

    /**
     * Create an instance of {@link SendDocumentRSType }
     * 
     */
    public SendDocumentRSType createSendDocumentRSType() {
        return new SendDocumentRSType();
    }

    /**
     * Create an instance of {@link SendDocumentRQType }
     * 
     */
    public SendDocumentRQType createSendDocumentRQType() {
        return new SendDocumentRQType();
    }

    /**
     * Create an instance of {@link DocumentInfoType }
     * 
     */
    public DocumentInfoType createDocumentInfoType() {
        return new DocumentInfoType();
    }

    /**
     * Create an instance of {@link ContentType }
     * 
     */
    public ContentType createContentType() {
        return new ContentType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendDocumentRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nequi.co/message/bussines/services/senddocument", name = "sendDocumentRS")
    public JAXBElement<SendDocumentRSType> createSendDocumentRS(SendDocumentRSType value) {
        return new JAXBElement<SendDocumentRSType>(_SendDocumentRS_QNAME, SendDocumentRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendDocumentRQType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nequi.co/message/bussines/services/senddocument", name = "sendDocumentRQ")
    public JAXBElement<SendDocumentRQType> createSendDocumentRQ(SendDocumentRQType value) {
        return new JAXBElement<SendDocumentRQType>(_SendDocumentRQ_QNAME, SendDocumentRQType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAvailabilityRQType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nequi.co/message/bussines/services/checkavailability", name = "checkAvailabilityRQ")
    public JAXBElement<CheckAvailabilityRQType> createCheckAvailabilityRQ(CheckAvailabilityRQType value) {
        return new JAXBElement<CheckAvailabilityRQType>(_CheckAvailabilityRQ_QNAME, CheckAvailabilityRQType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseMessageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nequi.co/message/bussines/services/", name = "responseMessage")
    public JAXBElement<ResponseMessageType> createResponseMessage(ResponseMessageType value) {
        return new JAXBElement<ResponseMessageType>(_ResponseMessage_QNAME, ResponseMessageType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckAvailabilityRSType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nequi.co/message/bussines/services/checkavailability", name = "checkAvailabilityRS")
    public JAXBElement<CheckAvailabilityRSType> createCheckAvailabilityRS(CheckAvailabilityRSType value) {
        return new JAXBElement<CheckAvailabilityRSType>(_CheckAvailabilityRS_QNAME, CheckAvailabilityRSType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestMessageType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://nequi.co/message/bussines/services/", name = "requestMessage")
    public JAXBElement<RequestMessageType> createRequestMessage(RequestMessageType value) {
        return new JAXBElement<RequestMessageType>(_RequestMessage_QNAME, RequestMessageType.class, null, value);
    }

}
