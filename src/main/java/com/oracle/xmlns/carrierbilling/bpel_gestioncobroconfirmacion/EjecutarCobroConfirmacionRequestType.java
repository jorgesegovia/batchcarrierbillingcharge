
package com.oracle.xmlns.carrierbilling.bpel_gestioncobroconfirmacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import pe.com.entel.soa.data.servicio.carrierbilling.v1.CarrierBillingCobroConfirmacionType;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="datosCobro" type="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}CarrierBillingCobroConfirmacionType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "datosCobro" })
@XmlRootElement(name = "ejecutarCobroConfirmacionRequestType")
public class EjecutarCobroConfirmacionRequestType {

    @XmlElement(required = true)
    protected CarrierBillingCobroConfirmacionType datosCobro;

    /**
     * Gets the value of the datosCobro property.
     *
     * @return
     *     possible object is
     *     {@link CarrierBillingCobroConfirmacionType }
     *
     */
    public CarrierBillingCobroConfirmacionType getDatosCobro() {
        return datosCobro;
    }

    /**
     * Sets the value of the datosCobro property.
     *
     * @param value
     *     allowed object is
     *     {@link CarrierBillingCobroConfirmacionType }
     *
     */
    public void setDatosCobro(CarrierBillingCobroConfirmacionType value) {
        this.datosCobro = value;
    }

}
