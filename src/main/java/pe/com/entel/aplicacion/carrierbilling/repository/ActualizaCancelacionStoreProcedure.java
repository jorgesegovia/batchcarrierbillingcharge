package pe.com.entel.aplicacion.carrierbilling.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizarCancelacionSp;

import java.util.Map;

public class ActualizaCancelacionStoreProcedure extends StoredProcedure {

    private String fullName;

    private String inParamName1;

    private String inParamName2;
    
    private String inParamName3;

    private String outParamName1;

    private String outParamName2;

    public ActualizaCancelacionStoreProcedure(JdbcTemplate jdbcTemplate,
                                              String fullName,
                                              String inParamName1,
                                              String inParamName2,
                                              String inParamName3,
                                              String outParamName1,
                                              String outParamName2) {
        super(jdbcTemplate, fullName);

        logger.info("fullName: " + fullName);
        logger.debug("inParamName1: " + inParamName1);
        logger.debug("inParamName2: " + inParamName2);
        logger.debug("inParamName3: " + inParamName3);
        logger.debug("outParamName1: " + outParamName1);
        logger.debug("outParamName2: " + outParamName2);

        this.fullName = fullName;
        this.inParamName1 = inParamName1;
        this.inParamName2 = inParamName2;
        this.inParamName3 = inParamName3;
        this.outParamName1 = outParamName1;
        this.outParamName2 = outParamName2;

        SqlParameter paramIn1 = new SqlParameter(inParamName1, OracleTypes.NUMBER);
        SqlParameter paramIn2 = new SqlParameter(inParamName2, OracleTypes.VARCHAR);
        SqlParameter paramIn3 = new SqlParameter(inParamName3, OracleTypes.VARCHAR);
        SqlOutParameter paramOut1 = new SqlOutParameter(outParamName1, OracleTypes.VARCHAR);
        SqlOutParameter paramOut2 = new SqlOutParameter(outParamName2, OracleTypes.VARCHAR);
        SqlParameter[] paramArray = {paramIn1, paramIn2, paramIn3, paramOut1, paramOut2};
        setFunction(false);
        setParameters(paramArray);
    }

    public ActualizarCancelacionSp run (ActualizarCancelacionSp o) throws Exception {

        logger.debug("IN: " + inParamName1 + " -> " + o.getIdsuscripcion());
        logger.debug("IN: " + inParamName2+ " -> " + o.getEstado());
        logger.debug("IN: " + inParamName3 + " -> " + o.getModificadoPor());

        Map spResult = this.execute(o.getIdsuscripcion(), o.getEstado(), o.getModificadoPor());

        logger.info("OUT: " + outParamName1 + " -> " + spResult.get(outParamName1));
        logger.info("OUT: " + outParamName2 + " -> " + spResult.get(outParamName2));

        o.setCodigorpta(String.valueOf(spResult.get(outParamName1)));
        o.setMensaje(String.valueOf(spResult.get(outParamName2)));

        return o;
    }
}
