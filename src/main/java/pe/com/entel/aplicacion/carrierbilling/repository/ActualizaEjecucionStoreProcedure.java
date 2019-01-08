package pe.com.entel.aplicacion.carrierbilling.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import pe.com.entel.aplicacion.carrierbilling.domain.ActualizaEjecucionSp;

import java.util.Map;

/**
 * @author jsegovia
 * @version 1.0, 12/26/18
 */
public class ActualizaEjecucionStoreProcedure extends StoredProcedure {

    private String fullName;

    private String inParamName1;

    private String inParamName2;

    private String inParamName3;

    private String inParamName4;

    private String inParamName5;

    private String inParamName6;

    private String outParamName7;

    private String outParamName8;

    public ActualizaEjecucionStoreProcedure(JdbcTemplate jdbcTemplate,
                                            String fullName,
                                            String inParamName1,
                                            String inParamName2,
                                            String inParamName3,
                                            String inParamName4,
                                            String inParamName5,
                                            String inParamName6,
                                            String outParamName7,
                                            String outParamName8) {
        super(jdbcTemplate, fullName);

        logger.info("Ejecutando procedure: " + fullName);
        logger.debug("inParamName1: " + inParamName1);
        logger.debug("inParamName2: " + inParamName2);
        logger.debug("inParamName3: " + inParamName3);
        logger.debug("inParamName4: " + inParamName4);
        logger.debug("inParamName5: " + inParamName5);
        logger.debug("inParamName6: " + inParamName6);
        logger.debug("outParamName3: " + outParamName7);
        logger.debug("outParamName4: " + outParamName8);

        this.fullName = fullName;
        this.inParamName1 = inParamName1;
        this.inParamName2 = inParamName2;
        this.inParamName3 = inParamName3;
        this.inParamName4 = inParamName4;
        this.inParamName5 = inParamName5;
        this.inParamName6 = inParamName6;
        this.outParamName7 = outParamName7;
        this.outParamName8 = outParamName8;

        SqlParameter paramIn1 = new SqlParameter(inParamName1, OracleTypes.NUMBER);
        SqlParameter paramIn2 = new SqlParameter(inParamName2, OracleTypes.VARCHAR);
        SqlParameter paramIn3 = new SqlParameter(inParamName3, OracleTypes.VARCHAR);
        SqlParameter paramIn4 = new SqlParameter(inParamName4, OracleTypes.VARCHAR);
        SqlParameter paramIn5 = new SqlParameter(inParamName5, OracleTypes.VARCHAR);
        SqlParameter paramIn6 = new SqlParameter(inParamName6, OracleTypes.NUMBER);
        SqlOutParameter paramOut7 = new SqlOutParameter(outParamName7, OracleTypes.VARCHAR);
        SqlOutParameter paramOut8 = new SqlOutParameter(outParamName8, OracleTypes.VARCHAR);
        SqlParameter[] paramArray = {paramIn1, paramIn2, paramIn3, paramIn4, paramIn5, paramIn6, paramOut7, paramOut8};
        setFunction(false);
        setParameters(paramArray);
    }

    public ActualizaEjecucionSp run(ActualizaEjecucionSp o) throws Exception {

        logger.info("IN: " + inParamName1 + " <- " + o.getIdBillControl());
        logger.info("IN: " + inParamName2 + " <- " + o.getSuscripcionOk());
        logger.info("IN: " + inParamName3 + " <- " + o.getSuscripcionError());
        logger.info("IN: " + inParamName4 + " <- " + o.getSuscripcionReintento());
        logger.info("IN: " + inParamName5 + " <- " + o.getModificadoPor());
        logger.info("IN: " + inParamName6 + " <- " + o.getTiempoTotal());

        Map spResult = this.execute(o.getIdBillControl(), o.getSuscripcionOk(),
                o.getSuscripcionError(), o.getSuscripcionReintento(), o.getModificadoPor(), o.getTiempoTotal());

        logger.info("OUT: " + outParamName7 + " -> " + spResult.get(outParamName7));
        logger.info("OUT: " + outParamName8 + " -> " + spResult.get(outParamName8));

        o.setCodigoRpta(String.valueOf(spResult.get(outParamName7)));
        o.setMensaje(String.valueOf(spResult.get(outParamName8)));

        return o;
    }

}
