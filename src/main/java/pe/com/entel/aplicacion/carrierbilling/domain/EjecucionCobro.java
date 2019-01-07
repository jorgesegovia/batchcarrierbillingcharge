package pe.com.entel.aplicacion.carrierbilling.domain;

import java.util.Date;

/**
 * @author jsegovia
 * @version 1.0, 12/28/18
 */
public class EjecucionCobro {

    private Suscripcion suscripcion;

    private String wscodrpta;

    private String wsdescripcionrpta;

    private Date wsejecucion;

    private int wshttpstatus;

    private String estadocobro;

    private String servicioejec;

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public String getWscodrpta() {
        return wscodrpta;
    }

    public void setWscodrpta(String wscodrpta) {
        this.wscodrpta = wscodrpta;
    }

    public String getWsdescripcionrpta() {
        return wsdescripcionrpta;
    }

    public void setWsdescripcionrpta(String wsdescripcionrpta) {
        this.wsdescripcionrpta = wsdescripcionrpta;
    }

    public Date getWsejecucion() {
        return wsejecucion;
    }

    public void setWsejecucion(Date wsejecucion) {
        this.wsejecucion = wsejecucion;
    }

    public int getWshttpstatus() {
        return wshttpstatus;
    }

    public void setWshttpstatus(int wshttpstatus) {
        this.wshttpstatus = wshttpstatus;
    }

    public String getEstadocobro() {
        return estadocobro;
    }

    public void setEstadocobro(String estadocobro) {
        this.estadocobro = estadocobro;
    }

    public String getServicioejec() {
        return servicioejec;
    }

    public void setServicioejec(String servicioejec) {
        this.servicioejec = servicioejec;
    }

}
