
package pe.com.entel.soa.data.generico.responsestatus.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ResponseStatus complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ResponseStatus"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="estado" type="{http://entel.com.pe/soa/data/generico/responseStatus/v1/}estadoType"/&gt;
 *         &lt;element name="codigoRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descripcionRespuesta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ubicacionError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="errorOrigen" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseStatus", propOrder = {
         "estado", "codigoRespuesta", "descripcionRespuesta", "ubicacionError", "fecha", "errorOrigen"
    })
public class ResponseStatus {

    @XmlElement(required = true)
    protected String estado;
    @XmlElement(required = true)
    protected String codigoRespuesta;
    @XmlElement(required = true)
    protected String descripcionRespuesta;
    protected String ubicacionError;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    protected Object errorOrigen;

    /**
     * Gets the value of the estado property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Gets the value of the codigoRespuesta property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    /**
     * Sets the value of the codigoRespuesta property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodigoRespuesta(String value) {
        this.codigoRespuesta = value;
    }

    /**
     * Gets the value of the descripcionRespuesta property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDescripcionRespuesta() {
        return descripcionRespuesta;
    }

    /**
     * Sets the value of the descripcionRespuesta property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDescripcionRespuesta(String value) {
        this.descripcionRespuesta = value;
    }

    /**
     * Gets the value of the ubicacionError property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUbicacionError() {
        return ubicacionError;
    }

    /**
     * Sets the value of the ubicacionError property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUbicacionError(String value) {
        this.ubicacionError = value;
    }

    /**
     * Gets the value of the fecha property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the errorOrigen property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getErrorOrigen() {
        return errorOrigen;
    }

    /**
     * Sets the value of the errorOrigen property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setErrorOrigen(Object value) {
        this.errorOrigen = value;
    }

}
