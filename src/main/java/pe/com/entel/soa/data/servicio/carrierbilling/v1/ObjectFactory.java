
package pe.com.entel.soa.data.servicio.carrierbilling.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the pe.com.entel.soa.data.servicio.carrierbilling.v1 package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TransactionId_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "transactionId");
    private final static QName _Aplicacion_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "aplicacion");
    private final static QName _TipoContenido_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "tipoContenido");
    private final static QName _Valor_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "valor");
    private final static QName _Producto_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "producto");
    private final static QName _RequestId_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "requestId");
    private final static QName _BangoTransactionId_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "bangoTransactionId");
    private final static QName _MerchantTransactionId_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "merchantTransactionId");
    private final static QName _UserId_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "userId");
    private final static QName _Amount_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "amount");
    private final static QName _Currency_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "currency");
    private final static QName _MerchantAccountKey_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "merchantAccountKey");
    private final static QName _ProductKey_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "productKey");
    private final static QName _ProductDescription_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "productDescription");
    private final static QName _ProductCategory_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "productCategory");
    private final static QName _SupportContact_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "supportContact");
    private final static QName _PaymentProviderTransactionId_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "paymentProviderTransactionId");
    private final static QName _IdCuentaCompartida_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "idCuentaCompartida");
    private final static QName _NombreOferta_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "nombreOferta");
    private final static QName _HoraInicio_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "horaInicio");
    private final static QName _IdOrden_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "idOrden");
    private final static QName _FechaEntregaOferta_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "fechaEntregaOferta");
    private final static QName _FechaRenovacion_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "fechaRenovacion");
    private final static QName _FechaCaducidad_QNAME =
        new QName("http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", "fechaCaducidad");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pe.com.entel.soa.data.servicio.carrierbilling.v1
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CarrierBillingCobroType }
     *
     */
    public CarrierBillingCobroType createCarrierBillingCobroType() {
        return new CarrierBillingCobroType();
    }

    /**
     * Create an instance of {@link CarrierBillingReservaType }
     *
     */
    public CarrierBillingReservaType createCarrierBillingReservaType() {
        return new CarrierBillingReservaType();
    }

    /**
     * Create an instance of {@link CarrierBillingConfirmacionType }
     *
     */
    public CarrierBillingConfirmacionType createCarrierBillingConfirmacionType() {
        return new CarrierBillingConfirmacionType();
    }

    /**
     * Create an instance of {@link CarrierBillingCancelacionType }
     *
     */
    public CarrierBillingCancelacionType createCarrierBillingCancelacionType() {
        return new CarrierBillingCancelacionType();
    }

    /**
     * Create an instance of {@link CarrierBillingDevolucionType }
     *
     */
    public CarrierBillingDevolucionType createCarrierBillingDevolucionType() {
        return new CarrierBillingDevolucionType();
    }

    /**
     * Create an instance of {@link CarrierBillingCobroConfirmacionType }
     *
     */
    public CarrierBillingCobroConfirmacionType createCarrierBillingCobroConfirmacionType() {
        return new CarrierBillingCobroConfirmacionType();
    }

    /**
     * Create an instance of {@link ConfirmarSuscripcionType }
     *
     */
    public ConfirmarSuscripcionType createConfirmarSuscripcionType() {
        return new ConfirmarSuscripcionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "transactionId")
    public JAXBElement<String> createTransactionId(String value) {
        return new JAXBElement<String>(_TransactionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "aplicacion")
    public JAXBElement<String> createAplicacion(String value) {
        return new JAXBElement<String>(_Aplicacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "tipoContenido")
    public JAXBElement<String> createTipoContenido(String value) {
        return new JAXBElement<String>(_TipoContenido_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "valor")
    public JAXBElement<Long> createValor(Long value) {
        return new JAXBElement<Long>(_Valor_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "producto")
    public JAXBElement<String> createProducto(String value) {
        return new JAXBElement<String>(_Producto_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "requestId")
    public JAXBElement<String> createRequestId(String value) {
        return new JAXBElement<String>(_RequestId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "bangoTransactionId")
    public JAXBElement<String> createBangoTransactionId(String value) {
        return new JAXBElement<String>(_BangoTransactionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/",
                    name = "merchantTransactionId")
    public JAXBElement<String> createMerchantTransactionId(String value) {
        return new JAXBElement<String>(_MerchantTransactionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "userId")
    public JAXBElement<String> createUserId(String value) {
        return new JAXBElement<String>(_UserId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "amount")
    public JAXBElement<String> createAmount(String value) {
        return new JAXBElement<String>(_Amount_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "currency")
    public JAXBElement<String> createCurrency(String value) {
        return new JAXBElement<String>(_Currency_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "merchantAccountKey")
    public JAXBElement<String> createMerchantAccountKey(String value) {
        return new JAXBElement<String>(_MerchantAccountKey_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "productKey")
    public JAXBElement<String> createProductKey(String value) {
        return new JAXBElement<String>(_ProductKey_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "productDescription")
    public JAXBElement<String> createProductDescription(String value) {
        return new JAXBElement<String>(_ProductDescription_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "productCategory")
    public JAXBElement<String> createProductCategory(String value) {
        return new JAXBElement<String>(_ProductCategory_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "supportContact")
    public JAXBElement<String> createSupportContact(String value) {
        return new JAXBElement<String>(_SupportContact_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/",
                    name = "paymentProviderTransactionId")
    public JAXBElement<String> createPaymentProviderTransactionId(String value) {
        return new JAXBElement<String>(_PaymentProviderTransactionId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "idCuentaCompartida")
    public JAXBElement<String> createIdCuentaCompartida(String value) {
        return new JAXBElement<String>(_IdCuentaCompartida_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "nombreOferta")
    public JAXBElement<String> createNombreOferta(String value) {
        return new JAXBElement<String>(_NombreOferta_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "horaInicio")
    public JAXBElement<String> createHoraInicio(String value) {
        return new JAXBElement<String>(_HoraInicio_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "idOrden")
    public JAXBElement<String> createIdOrden(String value) {
        return new JAXBElement<String>(_IdOrden_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "fechaEntregaOferta")
    public JAXBElement<String> createFechaEntregaOferta(String value) {
        return new JAXBElement<String>(_FechaEntregaOferta_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "fechaRenovacion")
    public JAXBElement<String> createFechaRenovacion(String value) {
        return new JAXBElement<String>(_FechaRenovacion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://entel.com.pe/soa/data/servicio/carrierbilling/v1/", name = "fechaCaducidad")
    public JAXBElement<String> createFechaCaducidad(String value) {
        return new JAXBElement<String>(_FechaCaducidad_QNAME, String.class, null, value);
    }

}
