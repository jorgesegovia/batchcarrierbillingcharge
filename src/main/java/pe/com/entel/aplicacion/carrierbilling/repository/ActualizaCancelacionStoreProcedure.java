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

    private String outParamName2;

    private String outParamName3;

    public ActualizaCancelacionStoreProcedure(JdbcTemplate jdbcTemplate,
                                              String fullName,
                                              String inParamName1,
                                              String outParamName2,
                                              String outParamName3) {
        super(jdbcTemplate, fullName);

        logger.info("fullName: " + fullName);
        logger.debug("inParamName1: " + inParamName1);
        logger.debug("outParamName2: " + outParamName2);
        logger.debug("outParamName3: " + outParamName3);

        this.fullName = fullName;
        this.inParamName1 = inParamName1;
        this.outParamName2 = outParamName2;
        this.outParamName3 = outParamName3;

        SqlParameter paramIn1 = new SqlParameter(inParamName1, OracleTypes.NUMBER);
        SqlOutParameter paramOut2 = new SqlOutParameter(outParamName2, OracleTypes.VARCHAR);
        SqlOutParameter paramOut3 = new SqlOutParameter(outParamName3, OracleTypes.VARCHAR);
        SqlParameter[] paramArray = {paramIn1, paramOut2, paramOut3};
        setFunction(false);
        setParameters(paramArray);
    }

    public ActualizarCancelacionSp run (ActualizarCancelacionSp o) throws Exception {

        logger.debug("IN: " + inParamName1 + " -> " + o.getIdsuscripcion());

        Map spResult = this.execute(o.getIdsuscripcion());

        logger.info("OUT: " + outParamName2 + " -> " + spResult.get(outParamName2));
        logger.info("OUT: " + outParamName3 + " -> " + spResult.get(outParamName3));

        o.setCodigorpta(String.valueOf(spResult.get(outParamName2)));
        o.setMensaje(String.valueOf(spResult.get(outParamName3)));

        return o;
    }
}
