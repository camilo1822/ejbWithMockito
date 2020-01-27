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
 * <p>Clase Java para requestHeaderType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="requestHeaderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="destination" type="{http://nequi.co/message/bussines/services/}destinationType"/>
 *         &lt;element name="messageID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channel" type="{http://nequi.co/message/bussines/services/}channelType"/>
 *         &lt;element name="consumer" type="{http://nequi.co/message/bussines/services/}consumerType"/>
 *         &lt;element name="container" type="{http://nequi.co/message/bussines/services/}containerType" minOccurs="0"/>
 *         &lt;element name="security" type="{http://nequi.co/message/bussines/services/}securityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestHeaderType", propOrder = {
    "destination",
    "messageID",
    "requestDate",
    "channel",
    "consumer",
    "container",
    "security"
})
public class RequestHeaderType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected DestinationType destination;
    @XmlElement(required = true)
    protected String messageID;
    @XmlElement(required = true)
    protected String requestDate;
    @XmlElement(required = true)
    protected ChannelType channel;
    @XmlElement(required = true)
    protected ConsumerType consumer;
    protected ContainerType container;
    protected SecurityType security;

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
     * Obtiene el valor de la propiedad requestDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * Establece el valor de la propiedad requestDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestDate(String value) {
        this.requestDate = value;
    }

    /**
     * Obtiene el valor de la propiedad channel.
     * 
     * @return
     *     possible object is
     *     {@link ChannelType }
     *     
     */
    public ChannelType getChannel() {
        return channel;
    }

    /**
     * Establece el valor de la propiedad channel.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelType }
     *     
     */
    public void setChannel(ChannelType value) {
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
     * Obtiene el valor de la propiedad security.
     * 
     * @return
     *     possible object is
     *     {@link SecurityType }
     *     
     */
    public SecurityType getSecurity() {
        return security;
    }

    /**
     * Establece el valor de la propiedad security.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityType }
     *     
     */
    public void setSecurity(SecurityType value) {
        this.security = value;
    }

}
