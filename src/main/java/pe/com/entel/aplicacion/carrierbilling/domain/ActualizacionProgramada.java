package pe.com.entel.aplicacion.carrierbilling.domain;

public class ActualizacionProgramada {

	private int idSuscripcion;
	private int idSuscripcionActProg;
	private String shareAccountId;
	private String canal;
	private String estado;
	
	public int getIdSuscripcion() {
		return idSuscripcion;
	}
	public void setIdSuscripcion(int idSuscripcion) {
		this.idSuscripcion = idSuscripcion;
	}
	public int getIdSuscripcionActProg() {
		return idSuscripcionActProg;
	}
	public void setIdSuscripcionActProg(int idSuscripcionActProg) {
		this.idSuscripcionActProg = idSuscripcionActProg;
	}
	public String getShareAccountId() {
		return shareAccountId;
	}
	public void setShareAccountId(String shareAccountId) {
		this.shareAccountId = shareAccountId;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
