package pe.com.entel.aplicacion.carrierbilling.service;

import org.apache.log4j.Logger;

import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaSuscripcionProgSp;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaSuscripcionSp;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizacionProgramada;
import pe.com.entel.aplicacion.carrierbilling.domain.Respuesta;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaSuscripcionProgStoreProcedure;
import pe.com.entel.aplicacion.carrierbilling.repository.ActualizaSuscripcionStoreProcedure;

public class ActualizarSuscripcionService {

	static Logger logger = Logger.getLogger(ActualizarSuscripcionService.class);

	private ActualizaSuscripcionProgStoreProcedure procedureActualizaSuscripcionProg;
	private ActualizaSuscripcionStoreProcedure procedureActualizaSuscripcion;
	private LimpiarCacheService limpiarCacheService;

	public void ejecutar(ActualizacionProgramada s) throws Exception {
		
		Respuesta respuesta = null;
		
		s = this.actualizaSuscripcion(s);
		respuesta = limpiarCacheService.ejecutar(s);
		logger.debug("Codigo de respuesta limpiar cache: " + respuesta.getCodigoRpta());
		if("0000".equals(respuesta.getCodigoRpta())) {
			this.actualizaSuscripcionProg(s.getIdSuscripcionActProg());
		}

	}

	private ActualizacionProgramada actualizaSuscripcion(ActualizacionProgramada s) throws Exception {
		logger.info("Actualizando suscripcion [ " + s.getIdSuscripcion() + " ] ....");
		
		ActualizaSuscripcionSp as = new ActualizaSuscripcionSp();
		as.setIdSuscripcionActProg(s.getIdSuscripcionActProg());
		as = procedureActualizaSuscripcion.run(as);
		s.setIdSuscripcion(as.getIdSuscripcion());
		s.setShareAccountId(as.getIdCuentaCompartida());

		logger.info("Actualizacion de suscripcion [ " + s.getIdSuscripcion() + " ] ejecutada correctamente!");
		return s;
	}
	
	private void actualizaSuscripcionProg(int idSuscripcionProg) throws Exception {
		logger.info("Actualizando suscripcion programada [ " + idSuscripcionProg + " ] ....");
		
		ActualizaSuscripcionProgSp asp = new ActualizaSuscripcionProgSp();
		asp.setIdSuscripcion(idSuscripcionProg);
		procedureActualizaSuscripcionProg.run(asp);
		
		logger.info("Actualizacion de suscripcion programada [ " + idSuscripcionProg + " ] ejecutada correctamente!");
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
