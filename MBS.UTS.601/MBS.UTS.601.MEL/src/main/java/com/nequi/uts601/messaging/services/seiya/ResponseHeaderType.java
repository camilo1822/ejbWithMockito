//
// Este archivo se genera mediante JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Cualquier modificación de este archivo se perderá después de volver a compilar el esquema de origen.
// Generado en: AM.09.25 en 10:24:53 AM COT 
//


package com.nequi.uts601.messaging.services.seiya;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para responseHeaderType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="destination" type="{http://nequi.co/message/bussines/services/}destinationType"/>
 *         &lt;element name="messageID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://nequi.co/message/bussines/services/}statusType"/>
 *         &lt;element name="responseDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provider" type="{http://nequi.co/message/bussines/services/}providerType"/>
 *         &lt;element name="container" type="{http://nequi.co/message/bussines/services/}containerType" minOccurs="0"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="consumer" type="{http://nequi.co/message/bussines/services/}consumerType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseHeaderType", propOrder = {
    "destination",
    "messageID",
    "status",
    "responseDate",
    "provider",
    "container",
    "channel",
    "consumer"
})
public class ResponseHeaderType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected DestinationType destination;
    @XmlElement(required = true)
    protected String messageID;
    @XmlElement(required = true)
    protected StatusType status;
    @XmlElement(required = true)
    protected String responseDate;
    @XmlElement(required = true)
    protected ProviderType provider;
    protected ContainerType container;
    @XmlElement(required = true)
    protected String channel;
    protected ConsumerType consumer;

    /**
     * Obtiene el valor de la propiedad destination.
     * 
     * @return
     *     possible object is
     *     {@link DestinationType }
     *     
     */
    public DestinationType getDestination() {
        return destination;
    }

    /**
     * Establece el valor de la propiedad destination.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationType }
     *     
     */
    public void setDestination(DestinationType value) {
        this.destination = value;
    }

    /**
     * Obtiene el valor de la propiedad messageID.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageID() {
        return messageID;
    }

    /**
     * Establece el valor de la propiedad messageID.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageID(String value) {
        this.messageID = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Establece el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad responseDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseDate() {
        return responseDate;
    }

    /**
     * Establece el valor de la propiedad responseDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseDate(String value) {
        this.responseDate = value;
    }

    /**
     * Obtiene el valor de la propiedad provider.
     * 
     * @return
     *     possible object is
     *     {@link ProviderType }
     *     
     */
    public ProviderType getProvider() {
        return provider;
    }

    /**
     * Establece el valor de la propiedad provider.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderType }
     *     
     */
    public void setProvider(ProviderType value) {
        this.provider = value;
    }

    /**
     * Obtiene el valor de la propiedad container.
     * 
     * @return
     *     possible object is
     *     {@link ContainerType }
     *     
     */
    public ContainerType getContainer() {
        return container;
    }

    /**
     * Establece el valor de la propiedad container.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerType }
     *     
     */
    public void setContainer(ContainerType value) {
        this.container = value;
    }

    /**
     * Obtiene el valor de la propiedad channel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Establece el valor de la propiedad channel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannel(String value) {
        this.channel = value;
    }

    /**
     * Obtiene el valor de la propiedad consumer.
     * 
     * @return
     *     possible object is
     *     {@link ConsumerType }
     *     
     */
    public ConsumerType getConsumer() {
        return consumer;
    }

    /**
     * Establece el valor de la propiedad consumer.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsumerType }
     *     
     */
    public void setConsumer(ConsumerType value) {
        this.consumer = value;
    }

}
