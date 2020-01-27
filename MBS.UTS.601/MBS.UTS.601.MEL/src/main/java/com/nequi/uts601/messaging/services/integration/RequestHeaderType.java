//
// Este archivo se genera mediante JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Consulte <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Cualquier modificación de este archivo se perderá después de volver a compilar el esquema de origen.
// Generado en: PM.09.24 en 02:07:05 PM COT 
//


package com.nequi.uts601.messaging.services.integration;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para RequestHeaderType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RequestHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="consumer" type="{http://nequi.co/message/integration/services/}consumerType"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="messageId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestDateTime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destination" type="{http://nequi.co/message/integration/services/}destinationType"/>
 *         &lt;element name="messageContext" type="{http://nequi.co/message/integration/services/}messageContextType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestHeaderType", propOrder = {
    "consumer",
    "channel",
    "messageId",
    "requestDateTime",
    "destination",
    "messageContext"
})
public class RequestHeaderType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected ConsumerType consumer;
    @XmlElement(required = true)
    protected String channel;
    @XmlElement(required = true)
    protected String messageId;
    @XmlElement(required = true)
    protected String requestDateTime;
    @XmlElement(required = true)
    protected DestinationType destination;
    protected MessageContextType messageContext;

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
     * Obtiene el valor de la propiedad messageId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Establece el valor de la propiedad messageId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Obtiene el valor de la propiedad requestDateTime.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * Establece el valor de la propiedad requestDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestDateTime(String value) {
        this.requestDateTime = value;
    }

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
     * Obtiene el valor de la propiedad messageContext.
     * 
     * @return
     *     possible object is
     *     {@link MessageContextType }
     *     
     */
    public MessageContextType getMessageContext() {
        return messageContext;
    }

    /**
     * Establece el valor de la propiedad messageContext.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageContextType }
     *     
     */
    public void setMessageContext(MessageContextType value) {
        this.messageContext = value;
    }

}
