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
 * <p>Clase Java para requestBodyType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="requestBodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://nequi.co/message/bussines/services/checkavailability}checkAvailabilityRQ"/>
 *         &lt;element ref="{http://nequi.co/message/bussines/services/senddocument}sendDocumentRQ"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestBodyType", propOrder = {
    "checkAvailabilityRQ",
    "sendDocumentRQ"
})
public class RequestBodyType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://nequi.co/message/bussines/services/checkavailability")
    protected CheckAvailabilityRQType checkAvailabilityRQ;
    @XmlElement(namespace = "http://nequi.co/message/bussines/services/senddocument")
    protected SendDocumentRQType sendDocumentRQ;

    /**
     * Obtiene el valor de la propiedad checkAvailabilityRQ.
     * 
     * @return
     *     possible object is
     *     {@link CheckAvailabilityRQType }
     *     
     */
    public CheckAvailabilityRQType getCheckAvailabilityRQ() {
        return checkAvailabilityRQ;
    }

    /**
     * Establece el valor de la propiedad checkAvailabilityRQ.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckAvailabilityRQType }
     *     
     */
    public void setCheckAvailabilityRQ(CheckAvailabilityRQType value) {
        this.checkAvailabilityRQ = value;
    }

    /**
     * Obtiene el valor de la propiedad sendDocumentRQ.
     * 
     * @return
     *     possible object is
     *     {@link SendDocumentRQType }
     *     
     */
    public SendDocumentRQType getSendDocumentRQ() {
        return sendDocumentRQ;
    }

    /**
     * Establece el valor de la propiedad sendDocumentRQ.
     * 
     * @param value
     *     allowed object is
     *     {@link SendDocumentRQType }
     *     
     */
    public void setSendDocumentRQ(SendDocumentRQType value) {
        this.sendDocumentRQ = value;
    }

}
