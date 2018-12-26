package pe.com.entel.aplicacion.carrierbilling.domain;

/**
 * @version 1.0, 19/12/2018
 * @autor jsegovia
 */
public class ActualizaCobroSp {

    private int idsuscripcion;
    private String estado;
    private String codigorpta;
    private String mensaje;

    public int getIdsuscripcion() {
        return idsuscripcion;
    }

    public void setIdsuscripcion(int idsuscripcion) {
        this.idsuscripcion = idsuscripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigorpta() {
        return codigorpta;
    }

    public void setCodigorpta(String codigorpta) {
        this.codigorpta = codigorpta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
