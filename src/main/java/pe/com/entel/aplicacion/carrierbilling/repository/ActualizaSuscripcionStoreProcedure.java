package pe.com.entel.aplicacion.carrierbilling.repository;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import oracle.jdbc.OracleTypes;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaSuscripcionSp;

public class ActualizaSuscripcionStoreProcedure extends StoredProcedure {

	private String fullName;

	private String inParamName1;

	private String outParamName2;
	
	private String outParamName3;
	
	private String outParamName4;

	private String outParamName5;

	public ActualizaSuscripcionStoreProcedure(JdbcTemplate jdbcTemplate, String fullName, String inParamName1,
			String outParamName2, String outParamName3, String outParamName4, String outParamName5) {
		super(jdbcTemplate, fullName);

		logger.debug("fullName: " + fullName);
		logger.debug("inParamName1: " + inParamName1);
		logger.debug("outParamName2: " + outParamName2);
		logger.debug("outParamName3: " + outParamName3);
		logger.debug("outParamName4: " + outParamName4);
		logger.debug("outParamName5: " + outParamName5);

		this.fullName = fullName;
		this.inParamName1 = inParamName1;
		this.outParamName2 = outParamName2;
		this.outParamName3 = outParamName3;
		this.outParamName4 = outParamName4;
		this.outParamName5 = outParamName5;

		SqlParameter paramIn1 = new SqlParameter(inParamName1, OracleTypes.NUMERIC);
		SqlOutParameter paramOut2 = new SqlOutParameter(outParamName2, OracleTypes.NUMERIC);
		SqlOutParameter paramOut3 = new SqlOutParameter(outParamName3, OracleTypes.VARCHAR);
		SqlOutParameter paramOut4 = new SqlOutParameter(outParamName4, OracleTypes.VARCHAR);
		SqlOutParameter paramOut5 = new SqlOutParameter(outParamName5, OracleTypes.VARCHAR);
		SqlParameter[] paramArray = { paramIn1, paramOut2, paramOut3, paramOut4, paramOut5 };
		setFunction(false);
		setParameters(paramArray);
	}

	public ActualizaSuscripcionSp run(ActualizaSuscripcionSp o) throws Exception {

		logger.debug("IN: " + inParamName1 + "-> " + o.getIdSuscripcionActProg());

		Map spResult = this.execute(o.getIdSuscripcionActProg());

		logger.debug("OUT: " + outParamName2 + " -> " + spResult.get(outParamName2));
		logger.debug("OUT: " + outParamName3 + " -> " + spResult.get(outParamName3));
		logger.debug("OUT: " + outParamName4 + " -> " + spResult.get(outParamName4));
		logger.debug("OUT: " + outParamName5 + " -> " + spResult.get(outParamName5));

		o.setIdSuscripcion(Integer.parseInt(String.valueOf(spResult.get(outParamName2))));
		o.setIdCuentaCompartida(String.valueOf(spResult.get(outParamName3)));
		o.setCodigoRpta(String.valueOf(spResult.get(outParamName4)));
		o.setMensaje(String.valueOf(spResult.get(outParamName5)));

		return o;
	}
}