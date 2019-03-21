package pe.com.entel.aplicacion.carrierbilling.service;

import org.apache.log4j.Logger;

import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaSuscripcionProgSp;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaSuscripcionSp;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaSuscripcionProgStoreProcedure;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaSuscripcionStoreProcedure;

public class ActualizarSuscripcionService {

	static Logger logger = Logger.getLogger(ActualizarSuscripcionService.class);

	private ActualizaSuscripcionProgStoreProcedure procedureActualizaSuscripcionProg;
	private ActualizaSuscripcionStoreProcedure procedureActualizaSuscripcion;
	private LimpiarCacheService limpiarCacheService;

	public void ejecutar(Suscripcion s) throws Exception {
		
		int idSuscripcionProg = 0;
		idSuscripcionProg = this.actualizaSuscripcion(s.getIdSuscripcion());
		limpiarCacheService.ejecutar(s);
		this.actualizaSuscripcionProg(idSuscripcionProg);

	}

	private int actualizaSuscripcion(int idSuscripcion) throws Exception {
		ActualizaSuscripcionSp as = new ActualizaSuscripcionSp();
		as.setIdSuscripcion(idSuscripcion);

		procedureActualizaSuscripcion.run(as);

		return as.getIdSuscripcionProgramada();
	}
	
	private void actualizaSuscripcionProg(int idSuscripcionProg) throws Exception {
		ActualizaSuscripcionProgSp asp = new ActualizaSuscripcionProgSp();
		asp.setIdSuscripcion(idSuscripcionProg);

		procedureActualizaSuscripcionProg.run(asp);

	}

	public ActualizaSuscripcionProgStoreProcedure getProcedureActualizaSuscripcionProg() {
		return procedureActualizaSuscripcionProg;
	}

	public void setProcedureActualizaSuscripcionProg(
			ActualizaSuscripcionProgStoreProcedure procedureActualizaSuscripcionProg) {
		this.procedureActualizaSuscripcionProg = procedureActualizaSuscripcionProg;
	}

	public ActualizaSuscripcionStoreProcedure getProcedureActualizaSuscripcion() {
		return procedureActualizaSuscripcion;
	}

	public void setProcedureActualizaSuscripcion(ActualizaSuscripcionStoreProcedure procedureActualizaSuscripcion) {
		this.procedureActualizaSuscripcion = procedureActualizaSuscripcion;
	}

	public LimpiarCacheService getLimpiarCacheService() {
		return limpiarCacheService;
	}

	public void setLimpiarCacheService(LimpiarCacheService limpiarCacheService) {
		this.limpiarCacheService = limpiarCacheService;
	}
	
}
