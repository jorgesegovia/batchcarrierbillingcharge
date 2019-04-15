package pe.com.entel.aplicacion.carrierbilling.domain;

public class ActualizaSuscripcionSp {

    private String codigoRpta;

    private String mensaje;
    
    private int idSuscripcionActProg;
    
    private int idSuscripcion;
     
    private String idCuentaCompartida;
    
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

	public int getIdSuscripcionActProg() {
		return idSuscripcionActProg;
	}

	public void setIdSuscripcionActProg(int idSuscripcionActProg) {
		this.idSuscripcionActProg = idSuscripcionActProg;
	}

	public int getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(int idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}

	public String getIdCuentaCompartida() {
		return idCuentaCompartida;
	}

	public void setIdCuentaCompartida(String idCuentaCompartida) {
		this.idCuentaCompartida = idCuentaCompartida;
	}
	
}
