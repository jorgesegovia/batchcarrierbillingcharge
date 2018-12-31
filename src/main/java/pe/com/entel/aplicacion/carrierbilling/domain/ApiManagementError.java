package pe.com.entel.aplicacion.carrierbilling.domain;

public class ApiManagementError {

	private String servicio;
	private String codigoError;
	private String descripcionError;
	private int codigoHttp;
	private String idCuentaCompartida;
	
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public String getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}
	public String getDescripcionError() {
		return descripcionError;
	}
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}
	public int getCodigoHttp() {
		return codigoHttp;
	}
	public void setCodigoHttp(int codigoHttp) {
		this.codigoHttp = codigoHttp;
	}
	public String getIdCuentaCompartida() {
		return idCuentaCompartida;
	}
	public void setIdCuentaCompartida(String idCuentaCompartida) {
		this.idCuentaCompartida = idCuentaCompartida;
	}
	
}
