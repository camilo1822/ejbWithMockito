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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://nequi.co/message/bussines/services/}responseMessage"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseMessage"
})
@XmlRootElement(name = "messageRS", namespace = "http://nequi.co/message/bussines/services/")
public class MessageRS
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected ResponseMessageType responseMessage;

    /**
     * Obtiene el valor de la propiedad responseMessage.
     * 
     * @return
     *     possible object is
     *     {@link ResponseMessageType }
     *     
     */
    public ResponseMessageType getResponseMessage() {
        return responseMessage;
    }

    /**
     * Establece el valor de la propiedad responseMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseMessageType }
     *     
     */
    public void setResponseMessage(ResponseMessageType value) {
        this.responseMessage = value;
    }

}
