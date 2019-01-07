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
    private String inParamName3;
    private String inParamName4;
    private String inParamName5;
    private String inParamName6;
    private String inParamName7;
    private String inParamName8;
    private String inParamName9;
    private String outParamName10;
    private String outParamName11;

    public ActualizaCobroStoreProcedure(JdbcTemplate jdbcTemplate,
                                        String fullName,
                                        String inParamName1,
                                        String inParamName2,
                                        String inParamName3,
                                        String inParamName4,
                                        String inParamName5,
                                        String inParamName6,
                                        String inParamName7,
                                        String inParamName8,
                                        String inParamName9,
                                        String outParamName10,
                                        String outParamName11) {
        super(jdbcTemplate, fullName);

        logger.debug("fullName: " + fullName);
        logger.debug("inParamName1: " + inParamName1);
        logger.debug("inParamName2: " + inParamName2);
        logger.debug("inParamName3: " + inParamName3);
        logger.debug("inParamName4: " + inParamName4);
        logger.debug("inParamName5: " + inParamName5);
        logger.debug("inParamName6: " + inParamName6);
        logger.debug("inParamName7: " + inParamName7);
        logger.debug("inParamName8: " + inParamName8);
        logger.debug("inParamName9: " + inParamName9);
        logger.debug("outParamName10: " + outParamName10);
        logger.debug("outParamName12: " + outParamName11);

        this.fullName = fullName;
        this.inParamName1 = inParamName1;
        this.inParamName2 = inParamName2;
        this.inParamName3 = inParamName3;
        this.inParamName4 = inParamName4;
        this.inParamName5 = inParamName5;
        this.inParamName6 = inParamName6;
        this.inParamName7 = inParamName7;
        this.inParamName8 = inParamName8;
        this.inParamName9 = inParamName9;
        this.outParamName10 = outParamName10;
        this.outParamName11 = outParamName11;

        SqlParameter paramIn1 = new SqlParameter(inParamName1, OracleTypes.NUMBER);
        SqlParameter paramIn2 = new SqlParameter(inParamName2, OracleTypes.NUMBER);
        SqlParameter paramIn3 = new SqlParameter(inParamName3, OracleTypes.VARCHAR);
        SqlParameter paramIn4 = new SqlParameter(inParamName4, OracleTypes.VARCHAR);
        SqlParameter paramIn5 = new SqlParameter(inParamName5, OracleTypes.VARCHAR);
        SqlParameter paramIn6 = new SqlParameter(inParamName6, OracleTypes.DATE);
        SqlParameter paramIn7 = new SqlParameter(inParamName7, OracleTypes.NUMBER);
        SqlParameter paramIn8 = new SqlParameter(inParamName8, OracleTypes.VARCHAR);
        SqlParameter paramIn9 = new SqlParameter(inParamName9, OracleTypes.VARCHAR);
        SqlOutParameter paramOut10 = new SqlOutParameter(outParamName10, OracleTypes.VARCHAR);
        SqlOutParameter paramOut11 = new SqlOutParameter(outParamName11, OracleTypes.VARCHAR);
        SqlParameter[] paramArray = {paramIn1, paramIn2, paramIn3, paramIn4, paramIn5,
                paramIn6, paramIn7, paramIn8, paramIn9, paramOut10, paramOut11};
        setFunction(false);
        setParameters(paramArray);
    }

    public ActualizaCobroSp run(ActualizaCobroSp o) throws Exception {

        logger.debug("IN: " + inParamName1 + " -> " + o.getIdbillcontrol());
        logger.debug("IN: " + inParamName2 + " -> " + o.getIdsuscripcion());
        logger.debug("IN: " + inParamName3 + " -> " + o.getEstado());
        logger.debug("IN: " + inParamName4 + " -> " + o.getWscodrpta());
        logger.debug("IN: " + inParamName5 + " -> " + o.getWsdescripcionrpta());
        logger.debug("IN: " + inParamName6 + " -> " + o.getWsejecucion());
        logger.debug("IN: " + inParamName7 + " -> " + o.getWshttpstatus());
        logger.debug("IN: " + inParamName8 + " -> " + o.getEstadocobro());
        logger.debug("IN: " + inParamName9 + " -> " + o.getServicioejec());

        Map spResult = this.execute(o.getIdbillcontrol(), o.getIdsuscripcion(), o.getEstado(), o.getWscodrpta(),
                o.getWsdescripcionrpta(), o.getWsejecucion(), o.getWshttpstatus(),
                o.getEstadocobro(), o.getServicioejec());

        logger.debug("OUT: " + outParamName10 + " -> " + spResult.get(outParamName10));
        logger.debug("OUT: " + outParamName11 + " -> " + spResult.get(outParamName11));

        o.setCodigorpta(String.valueOf(spResult.get(outParamName10)));
        o.setMensaje(String.valueOf(spResult.get(outParamName11)));

        return o;
    }

}
