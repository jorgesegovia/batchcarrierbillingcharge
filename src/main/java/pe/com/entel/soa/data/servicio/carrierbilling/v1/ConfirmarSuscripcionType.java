
package pe.com.entel.soa.data.servicio.carrierbilling.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConfirmarSuscripcionType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ConfirmarSuscripcionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}idCuentaCompartida" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}nombreOferta" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}horaInicio" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}idOrden" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}fechaEntregaOferta" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}fechaRenovacion" minOccurs="0"/&gt;
 *         &lt;element ref="{http://entel.com.pe/soa/data/servicio/carrierbilling/v1/}fechaCaducidad" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfirmarSuscripcionType", propOrder = {
         "idCuentaCompartida", "nombreOferta", "horaInicio", "idOrden", "fechaEntregaOferta", "fechaRenovacion",
         "fechaCaducidad"
    })
public class ConfirmarSuscripcionType {

    protected String idCuentaCompartida;
    protected String nombreOferta;
    protected String horaInicio;
    protected String idOrden;
    protected String fechaEntregaOferta;
    protected String fechaRenovacion;
    protected String fechaCaducidad;

    /**
     * Gets the value of the idCuentaCompartida property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdCuentaCompartida() {
        return idCuentaCompartida;
    }

    /**
     * Sets the value of the idCuentaCompartida property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdCuentaCompartida(String value) {
        this.idCuentaCompartida = value;
    }

    /**
     * Gets the value of the nombreOferta property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNombreOferta() {
        return nombreOferta;
    }

    /**
     * Sets the value of the nombreOferta property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNombreOferta(String value) {
        this.nombreOferta = value;
    }

    /**
     * Gets the value of the horaInicio property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * Sets the value of the horaInicio property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHoraInicio(String value) {
        this.horaInicio = value;
    }

    /**
     * Gets the value of the idOrden property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdOrden() {
        return idOrden;
    }

    /**
     * Sets the value of the idOrden property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIdOrden(String value) {
        this.idOrden = value;
    }

    /**
     * Gets the value of the fechaEntregaOferta property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechaEntregaOferta() {
        return fechaEntregaOferta;
    }

    /**
     * Sets the value of the fechaEntregaOferta property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechaEntregaOferta(String value) {
        this.fechaEntregaOferta = value;
    }

    /**
     * Gets the value of the fechaRenovacion property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechaRenovacion() {
        return fechaRenovacion;
    }

    /**
     * Sets the value of the fechaRenovacion property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechaRenovacion(String value) {
        this.fechaRenovacion = value;
    }

    /**
     * Gets the value of the fechaCaducidad property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * Sets the value of the fechaCaducidad property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFechaCaducidad(String value) {
        this.fechaCaducidad = value;
    }

}
