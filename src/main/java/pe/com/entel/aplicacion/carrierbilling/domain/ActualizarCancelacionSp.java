package pe.com.entel.aplicacion.carrierbilling.domain;

public class ActualizarCancelacionSp {

    private int idsuscripcion;
    private String estado;
    private String modificadoPor;
    private String codigorpta;
    private String mensaje;

    public int getIdsuscripcion() {
        return idsuscripcion;
    }

    public void setIdsuscripcion(int idsuscripcion) {
        this.idsuscripcion = idsuscripcion;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
    
}
