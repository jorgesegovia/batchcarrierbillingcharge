
package pe.com.entel.soa.data.generico.entelgenericheader.v1;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * <p>Java class for HeaderRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="HeaderRequestType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element name="canal" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="idAplicacion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idTransaccionESB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idTransaccionNegocio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="nodoAdicional" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;anyAttribute processContents='skip' namespace='##other'/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HeaderRequestType", propOrder = {
         "canal", "idAplicacion", "usuario", "idTransaccionESB", "idTransaccionNegocio", "fechaInicio", "nodoAdicional"
    })
public class HeaderRequestType {

    protected String canal;
    protected Long idAplicacion;
    protected String usuario;
    protected String idTransaccionESB;
    protected String idTransaccionNegocio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaInicio;
    protected Object nodoAdicional;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the canal property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCanal() {
        return canal;
    }

    /**
     * Sets the value of the canal property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCanal(String value) {
        this.canal = value;
    }

    /**
     * Gets the value of the idAplicacion property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getIdAplicacion() {
        return idAplicacion;
    }

    /**
     * Sets the value of the idAplicacion property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setIdAplicacion(Long value) {
        this.idAplicacion = value;
    }

    /**
     * Gets the value of the usuario property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

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

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     *
     * <p>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     *
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     *
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
