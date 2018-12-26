
package pe.com.entel.soa.data.generico.entelgenericheader.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for HeaderResponseType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="HeaderResponseType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idTransaccionESB" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="idTransaccionNegocio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="nodoAdicional" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderResponseType", propOrder = {
         "idTransaccionESB", "idTransaccionNegocio", "fechaInicio", "fechaFin", "nodoAdicional"
    })
public class HeaderResponseType {

    @XmlElement(required = true)
    protected String idTransaccionESB;
    @XmlElement(required = true)
    protected String idTransaccionNegocio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaFin;
    protected Object nodoAdicional;

    /**
     * Gets the value of the idTransaccionESB property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdTransaccionESB() {
        return idTransaccionESB;
    }

    /**
     * Sets the value of the idTransaccionESB property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdTransaccionESB(String value) {
        this.idTransaccionESB = value;
    }

    /**
     * Gets the value of the idTransaccionNegocio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdTransaccionNegocio() {
        return idTransaccionNegocio;
    }

    /**
     * Sets the value of the idTransaccionNegocio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdTransaccionNegocio(String value) {
        this.idTransaccionNegocio = value;
    }

    /**
     * Gets the value of the fechaInicio property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Sets the value of the fechaInicio property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Gets the value of the fechaFin property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getFechaFin() {
        return fechaFin;
    }

    /**
     * Sets the value of the fechaFin property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setFechaFin(XMLGregorianCalendar value) {
        this.fechaFin = value;
    }

    /**
     * Gets the value of the nodoAdicional property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getNodoAdicional() {
        return nodoAdicional;
    }

    /**
     * Sets the value of the nodoAdicional property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setNodoAdicional(Object value) {
        this.nodoAdicional = value;
    }

}
