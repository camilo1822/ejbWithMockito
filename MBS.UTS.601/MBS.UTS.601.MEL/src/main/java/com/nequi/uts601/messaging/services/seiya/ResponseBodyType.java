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
 * <p>Clase Java para responseBodyType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido esperado contenido en esta clase.
 * 
 * <pre>
 * &lt;complexType name="responseBodyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element ref="{http://nequi.co/message/bussines/services/checkavailability}checkAvailabilityRS"/>
 *         &lt;element ref="{http://nequi.co/message/bussines/services/senddocument}sendDocumentRS"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responseBodyType", propOrder = {
    "checkAvailabilityRS",
    "sendDocumentRS"
})
public class ResponseBodyType
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://nequi.co/message/bussines/services/checkavailability")
    protected CheckAvailabilityRSType checkAvailabilityRS;
    @XmlElement(namespace = "http://nequi.co/message/bussines/services/senddocument")
    protected SendDocumentRSType sendDocumentRS;

    /**
     * Obtiene el valor de la propiedad checkAvailabilityRS.
     * 
     * @return
     *     possible object is
     *     {@link CheckAvailabilityRSType }
     *     
     */
    public CheckAvailabilityRSType getCheckAvailabilityRS() {
        return checkAvailabilityRS;
    }

    /**
     * Establece el valor de la propiedad checkAvailabilityRS.
     * 
     * @param value
     *     allowed object is
     *     {@link CheckAvailabilityRSType }
     *     
     */
    public void setCheckAvailabilityRS(CheckAvailabilityRSType value) {
        this.checkAvailabilityRS = value;
    }

    /**
     * Obtiene el valor de la propiedad sendDocumentRS.
     * 
     * @return
     *     possible object is
     *     {@link SendDocumentRSType }
     *     
     */
    public SendDocumentRSType getSendDocumentRS() {
        return sendDocumentRS;
    }

    /**
     * Establece el valor de la propiedad sendDocumentRS.
     * 
     * @param value
     *     allowed object is
     *     {@link SendDocumentRSType }
     *     
     */
    public void setSendDocumentRS(SendDocumentRSType value) {
        this.sendDocumentRS = value;
    }

}
