package pe.com.entel.aplicacion.carrierbilling.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaCobroSp;

import java.util.Map;

/**
 * @version 1.0, 19/12/2018
 * @autor jsegovia
 */
public class ActualizaCobroStoreProcedure extends StoredProcedure {

    private String fullName;

    private String inParamName1;

    private String inParamName2;

    private String outParamName3;

    private String outParamName4;

    public ActualizaCobroStoreProcedure(JdbcTemplate jdbcTemplate,
                                        String fullName,
                                        String inParamName1,
                                        String inParamName2,
                                        String outParamName3,
                                        String outParamName4) {
        super(jdbcTemplate, fullName);

        logger.debug("fullName: " + fullName);
        logger.debug("inParamName1: " + inParamName1);
        logger.debug("inParamName2: " + inParamName2);
        logger.debug("outParamName3: " + outParamName3);
        logger.debug("outParamName4: " + outParamName4);

        this.fullName = fullName;
        this.inParamName1 = inParamName1;
        this.inParamName2 = inParamName2;
        this.outParamName3 = outParamName3;
        this.outParamName4 = outParamName4;

        SqlParameter paramIn1 = new SqlParameter(inParamName1, OracleTypes.NUMBER);
        SqlParameter paramIn2 = new SqlParameter(inParamName2, OracleTypes.VARCHAR);
        SqlOutParameter paramOut3 = new SqlOutParameter(outParamName3, OracleTypes.VARCHAR);
        SqlOutParameter paramOut4 = new SqlOutParameter(outParamName4, OracleTypes.VARCHAR);
        SqlParameter[] paramArray = {paramIn1, paramIn2, paramOut3, paramOut4};
        setFunction(false);
        setParameters(paramArray);
    }

    public ActualizaCobroSp run(ActualizaCobroSp o) throws Exception {

        logger.debug("IN: " + inParamName1 + " -> " + o.getIdsuscripcion());
        logger.debug("IN: " + inParamName2 + " -> " + o.getEstado());

        Map spResult = this.execute(o.getIdsuscripcion(), o.getEstado());

        logger.debug("OUT: " + outParamName3 + " -> " + spResult.get(outParamName3));
        logger.debug("OUT: " + outParamName4 + " -> " + spResult.get(outParamName4));

        o.setCodigorpta(String.valueOf(spResult.get(outParamName3)));
        o.setMensaje(String.valueOf(spResult.get(outParamName4)));

        return o;
    }

}
