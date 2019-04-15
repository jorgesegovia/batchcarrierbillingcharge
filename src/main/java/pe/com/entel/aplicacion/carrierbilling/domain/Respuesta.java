package pe.com.entel.aplicacion.carrierbilling.domain;

public class Respuesta {

    private String codigoRpta;

    private String mensaje;

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

	@Override
    public String toString() {
        return "ApiManagementError{" +
                "codigoRpta=" + codigoRpta +
                ", mensaje=" + mensaje +
                 '}';
    }
}
