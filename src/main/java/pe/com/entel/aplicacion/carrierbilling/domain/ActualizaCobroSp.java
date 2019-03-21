package pe.com.entel.aplicacion.carrierbilling.domain;

import java.util.Date;

/**
 * @version 1.0, 19/12/2018
 * @autor jsegovia
 */
public class ActualizaCobroSp {

    private int idbillcontrol;

    private int idsuscripcion;

    private String wscodrpta;

    private String wsdescripcionrpta;

    private Date wsejecucion;

    private int wshttpstatus;

    private String estadocobro;

    private String servicioejec;
    
    private String tipoEjecucion;

    private String codigorpta;

    private String mensaje;

    public int getIdbillcontrol() {
        return idbillcontrol;
    }

    public void setIdbillcontrol(int idbillcontrol) {
        this.idbillcontrol = idbillcontrol;
    }

    public int getIdsuscripcion() {
        return idsuscripcion;
    }

    public void setIdsuscripcion(int idsuscripcion) {
        this.idsuscripcion = idsuscripcion;
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

	public String getTipoEjecucion() {
		return tipoEjecucion;
	}

	public void setTipoEjecucion(String tipoEjecucion) {
		this.tipoEjecucion = tipoEjecucion;
	}
    
}
