
package pe.com.entel.soa.data.generico.entelfault.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EntelFault complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="EntelFault"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idAuditoria" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codigoError" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descripcionError" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "EntelFault", propOrder = {
         "idAuditoria", "codigoError", "descripcionError", "ubicacionError", "fecha", "errorOrigen" })
public class EntelFault {

    @XmlElement(required = true)
    protected String idAuditoria;
    @XmlElement(required = true)
    protected String codigoError;
    @XmlElement(required = true)
    protected String descripcionError;
    protected String ubicacionError;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    protected Object errorOrigen;

    /**
     * Gets the value of the idAuditoria property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdAuditoria() {
        return idAuditoria;
    }

    /**
     * Sets the value of the idAuditoria property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdAuditoria(String value) {
        this.idAuditoria = value;
    }

    /**
     * Gets the value of the codigoError property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the value of the codigoError property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCodigoError(String value) {
        this.codigoError = value;
    }

    /**
     * Gets the value of the descripcionError property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
     * Sets the value of the descripcionError property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDescripcionError(String value) {
        this.descripcionError = value;
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
