package pe.com.entel.aplicacion.carrierbilling.domain;

/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
public class Suscripcion {

    private int rowNum;
    private int billControl;
    private int idSuscripcion;
    private int idOferta;
    private double montoCobro;
    private String telefono;
    private int coId;
    private String idCliente;
    private String currency;
    private String merchantAccountKey;
    private String productKey;
    private String productDescription;
    private String productCategory;
    private String merchantTransactionId;
    private String canal;
    private String shareAccountId;
    private String codigorpta;
    private String descripcionrpta;
    private String paymentTransactionId;
    private String requestId;
    private String bangoTransactionId;
    private int idCanal;

    public String getShareAccountId() {
        return shareAccountId;
    }

    public void setShareAccountId(String shareAccountId) {
        this.shareAccountId = shareAccountId;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getBillControl() {
        return billControl;
    }

    public void setBillControl(int billControl) {
        this.billControl = billControl;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public double getMontoCobro() {
        return montoCobro;
    }

    public void setMontoCobro(double montoCobro) {
        this.montoCobro = montoCobro;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCoId() {
        return coId;
    }

    public void setCoId(int coId) {
        this.coId = coId;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchantAccountKey() {
        return merchantAccountKey;
    }

    public void setMerchantAccountKey(String merchantAccountKey) {
        this.merchantAccountKey = merchantAccountKey;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getCodigorpta() {
        return codigorpta;
    }

    public void setCodigorpta(String codigorpta) {
        this.codigorpta = codigorpta;
    }

    public String getDescripcionrpta() {
        return descripcionrpta;
    }

    public void setDescripcionrpta(String descripcionrpta) {
        this.descripcionrpta = descripcionrpta;
    }

    public String getPaymentTransactionId() {
        return paymentTransactionId;
    }

    public void setPaymentTransactionId(String paymentTransactionId) {
        this.paymentTransactionId = paymentTransactionId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getBangoTransactionId() {
        return bangoTransactionId;
    }

    public void setBangoTransactionId(String bangoTransactionId) {
        this.bangoTransactionId = bangoTransactionId;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

	@Override
    public String toString() {
        return "Suscripcion{" +
                "rowNum=" + rowNum +
                ", billControl=" + billControl +
                ", idSuscripcion=" + idSuscripcion +
                ", idOferta=" + idOferta +
                ", montoCobro=" + montoCobro +
                ", telefono='" + telefono + '\'' +
                ", coId=" + coId +
                ", idCliente='" + idCliente + '\'' +
                ", currency='" + currency + '\'' +
                ", merchantAccountKey='" + merchantAccountKey + '\'' +
                ", productKey='" + productKey + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", merchantTransactionId='" + merchantTransactionId + '\'' +
                ", canal='" + canal + '\'' +
                ", shareAccountId='" + shareAccountId + '\'' +
                ", codigorpta='" + codigorpta + '\'' +
                ", descripcionrpta='" + descripcionrpta + '\'' +
                ", paymentTransactionId='" + paymentTransactionId + '\'' +
                ", requestId='" + requestId + '\'' +
                ", bangoTransactionId='" + bangoTransactionId + '\'' +
                ", idCanal=" + idCanal +
                 '}';
    }
}
