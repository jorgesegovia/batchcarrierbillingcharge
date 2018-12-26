package pe.com.entel.aplicacion.carrierbilling.domain;

import java.util.Date;

/**
 * @version 1.0, 17/12/2018
 * @autor jsegovia
 */
public class InicioCobroSp {

    private Date fechaCobro;
    private String creadoPor;
    private int numeroPaginas;
    private String codigoRpta;
    private String mensaje;
    private int idBillControl;

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public String getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(String creadoPor) {
        this.creadoPor = creadoPor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
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

    public int getIdBillControl() {
        return idBillControl;
    }

    public void setIdBillControl(int idBillControl) {
        this.idBillControl = idBillControl;
    }

    @Override
    public String toString() {
        return "InicioCobroSp{" +
                "fechaCobro=" + fechaCobro +
                ", creadoPor='" + creadoPor + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", codigoRpta='" + codigoRpta + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", idBillControl=" + idBillControl +
                '}';
    }
}
