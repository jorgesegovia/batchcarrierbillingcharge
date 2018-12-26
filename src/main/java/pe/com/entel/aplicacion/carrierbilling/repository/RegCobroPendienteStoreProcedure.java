package pe.com.entel.aplicacion.carrierbilling.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import pe.com.entel.aplicacion.carrierbilling.domain.InicioCobroSp;

import java.util.Map;

/**
 * @version 1.0, 21/12/2018
 * @autor jsegovia
 */
public class RegCobroPendienteStoreProcedure extends StoredProcedure {

    private String fullName;

    private String inParamName1;

    private String inParamName2;

    private String outParamName3;

    private String outParamName4;

    private String outParamName5;

    private String outParamName6;

    public RegCobroPendienteStoreProcedure(JdbcTemplate jdbcTemplate,
                                           String fullName,
                                           String inParamName1,
                                           String inParamName2,
                                           String outParamName3,
                                           String outParamName4,
                                           String outParamName5,
                                           String outParamName6) {
        super(jdbcTemplate, fullName);

        logger.debug("fullName: " + fullName);
        logger.debug("inParamName1: " + inParamName1);
        logger.debug("inParamName2: " + inParamName2);
        logger.debug("outParamName3: " + outParamName3);
        logger.debug("outParamName4: " + outParamName4);
        logger.debug("outParamName5: " + outParamName5);
        logger.debug("outParamName6: " + outParamName6);

        this.fullName = fullName;
        this.inParamName1 = inParamName1;
        this.inParamName2 = inParamName2;
        this.outParamName3 = outParamName3;
        this.outParamName4 = outParamName4;
        this.outParamName5 = outParamName5;
        this.outParamName6 = outParamName6;

        SqlParameter paramIn1 = new SqlParameter(inParamName1, OracleTypes.DATE);
        SqlParameter paramIn2 = new SqlParameter(inParamName2, OracleTypes.VARCHAR);
        SqlOutParameter paramOut3 = new SqlOutParameter(outParamName3, OracleTypes.NUMERIC);
        SqlOutParameter paramOut4 = new SqlOutParameter(outParamName4, OracleTypes.NUMERIC);
        SqlOutParameter paramOut5 = new SqlOutParameter(outParamName5, OracleTypes.VARCHAR);
        SqlOutParameter paramOut6 = new SqlOutParameter(outParamName6, OracleTypes.VARCHAR);
        SqlParameter[] paramArray = {paramIn1, paramIn2, paramOut3, paramOut4, paramOut5, paramOut6};
        setFunction(false);
        setParameters(paramArray);
    }

    public InicioCobroSp run(InicioCobroSp o) throws Exception {

        logger.debug("IN: " + inParamName1 + " -> " + o.getFechaCobro());
        logger.debug("IN: " + inParamName2 + " -> " + o.getCreadoPor());

        Map spResult = this.execute(o.getFechaCobro(), o.getCreadoPor());

        logger.debug("OUT: " + outParamName3 + " -> " + spResult.get(outParamName3));
        logger.debug("OUT: " + outParamName4 + " -> " + spResult.get(outParamName4));
        logger.debug("OUT: " + outParamName5 + " -> " + spResult.get(outParamName5));
        logger.debug("OUT: " + outParamName6 + " -> " + spResult.get(outParamName6));

        o.setIdBillControl(Integer.parseInt(String.valueOf(spResult.get(outParamName3))));
        o.setNumeroPaginas(Integer.parseInt(String.valueOf(spResult.get(outParamName4))));
        o.setCodigoRpta(String.valueOf(spResult.get(outParamName5)));
        o.setMensaje(String.valueOf(spResult.get(outParamName6)));

        return o;
    }

}
