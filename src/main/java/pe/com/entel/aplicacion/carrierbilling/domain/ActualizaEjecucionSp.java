package pe.com.entel.aplicacion.carrierbilling.domain;

/**
 * @author jsegovia
 * @version 1.0, 12/26/18
 */
public class ActualizaEjecucionSp {

    private int idBillControl;

    private int suscripcionOk;

    private int suscripcionError;

    private int suscripcionReintento;

    private String modificadoPor;

    private String codigoRpta;

    private String mensaje;

    public int getIdBillControl() {
        return idBillControl;
    }

    public void setIdBillControl(int idBillControl) {
        this.idBillControl = idBillControl;
    }

    public int getSuscripcionOk() {
        return suscripcionOk;
    }

    public void setSuscripcionOk(int suscripcionOk) {
        this.suscripcionOk = suscripcionOk;
    }

    public int getSuscripcionError() {
        return suscripcionError;
    }

    public void setSuscripcionError(int suscripcionError) {
        this.suscripcionError = suscripcionError;
    }

    public int getSuscripcionReintento() {
        return suscripcionReintento;
    }

    public void setSuscripcionReintento(int suscripcionReintento) {
        this.suscripcionReintento = suscripcionReintento;
    }

    public String getCodigoRpta() {
        return codigoRpta;
    }

    public void setCodigoRpta(String codigoRpta) {
        this.codigoRpta = codigoRpta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }
}
