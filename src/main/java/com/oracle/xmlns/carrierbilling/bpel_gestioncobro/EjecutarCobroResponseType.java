
package com.oracle.xmlns.carrierbilling.bpel_gestioncobro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import pe.com.entel.soa.data.generico.responsestatus.v1.ResponseStatus;


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
 *         &lt;element name="responseStatus" type="{http://entel.com.pe/soa/data/generico/responseStatus/v1/}ResponseStatus"/&gt;
 *         &lt;element name="responseData"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="idtransacccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "responseStatus", "responseData" })
@XmlRootElement(name = "ejecutarCobroResponseType")
public class EjecutarCobroResponseType {

    @XmlElement(required = true)
    protected ResponseStatus responseStatus;
    @XmlElement(required = true)
    protected EjecutarCobroResponseType.ResponseData responseData;

    /**
     * Gets the value of the responseStatus property.
     *
     * @return
     *     possible object is
     *     {@link ResponseStatus }
     *
     */
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    /**
     * Sets the value of the responseStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseStatus }
     *
     */
    public void setResponseStatus(ResponseStatus value) {
        this.responseStatus = value;
    }

    /**
     * Gets the value of the responseData property.
     *
     * @return
     *     possible object is
     *     {@link EjecutarCobroResponseType.ResponseData }
     *
     */
    public EjecutarCobroResponseType.ResponseData getResponseData() {
        return responseData;
    }

    /**
     * Sets the value of the responseData property.
     *
     * @param value
     *     allowed object is
     *     {@link EjecutarCobroResponseType.ResponseData }
     *
     */
    public void setResponseData(EjecutarCobroResponseType.ResponseData value) {
        this.responseData = value;
    }


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
     *         &lt;element name="idtransacccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "idtransacccion" })
    public static class ResponseData {

        protected String idtransacccion;

        /**
         * Gets the value of the idtransacccion property.
         *
         * @return
         *     possible object is
         *     {@link String }
         *
         */
        public String getIdtransacccion() {
            return idtransacccion;
        }

        /**
         * Sets the value of the idtransacccion property.
         *
         * @param value
         *     allowed object is
         *     {@link String }
         *
         */
        public void setIdtransacccion(String value) {
            this.idtransacccion = value;
        }

    }

}
