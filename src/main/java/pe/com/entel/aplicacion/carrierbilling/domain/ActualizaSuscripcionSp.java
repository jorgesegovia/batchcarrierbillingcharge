package pe.com.entel.aplicacion.carrierbilling.domain;

public class ActualizaSuscripcionSp {

    private String codigoRpta;

    private String mensaje;
    
    private int idSuscripcionProgramada;
    
    private int idSuscripcion;

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

	public int getIdSuscripcionProgramada() {
		return idSuscripcionProgramada;
	}

	public void setIdSuscripcionProgramada(int idSuscripcionProgramada) {
		this.idSuscripcionProgramada = idSuscripcionProgramada;
	}

	public int getIdSuscripcion() {
		return idSuscripcion;
	}

	public void setIdSuscripcion(int idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}


}
