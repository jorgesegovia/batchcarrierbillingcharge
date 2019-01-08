package pe.com.entel.aplicacion.carrierbilling.repository;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import oracle.jdbc.OracleTypes;
import pe.com.entel.aplicacion.carrierbilling.domain.ApiManagementError;

public class InsertErrorCancelacionStoreProcedure extends StoredProcedure {

    private String fullName;
    
    private String inParamName1;

    private String inParamName2;
    
    private String inParamName3;
    
    private String inParamName4;

    private String outParamName1;
    
    private String outParamName2;

    public InsertErrorCancelacionStoreProcedure(JdbcTemplate jdbcTemplate,
                                              String fullName,
                                              String inParamName1,
                                              String inParamName2,
                                              String inParamName3,
                                              String inParamName4,
                                              String outParamName1,
                                              String outParamName2) {
        super(jdbcTemplate, fullName);

        logger.info("fullName: " + fullName);
        logger.debug("inParamName1: " + inParamName1);
        logger.debug("inParamName2: " + inParamName2);
        logger.debug("inParamName3: " + inParamName3);
        logger.debug("inParamName4: " + inParamName4);
        logger.debug("outParamName1: " + outParamName1);
        logger.debug("outParamName2: " + outParamName2);

        this.fullName = fullName;
        this.inParamName1 = inParamName1;
        this.inParamName2 = inParamName2;
        this.inParamName3 = inParamName3;
        this.inParamName4 = inParamName4;
        this.outParamName1 = outParamName1;
        this.outParamName2 = outParamName2;

        SqlParameter paramIn1 = new SqlParameter(inParamName1, OracleTypes.VARCHAR);
        SqlParameter paramIn2 = new SqlParameter(inParamName2, OracleTypes.VARCHAR);
        SqlParameter paramIn3 = new SqlParameter(inParamName3, OracleTypes.VARCHAR);
        SqlParameter paramIn4 = new SqlParameter(inParamName4, OracleTypes.NUMBER);
        SqlOutParameter paramOut5 = new SqlOutParameter(outParamName1, OracleTypes.VARCHAR);
        SqlOutParameter paramOut6 = new SqlOutParameter(outParamName2, OracleTypes.VARCHAR);
        SqlParameter[] paramArray = {paramIn1, paramIn2, paramIn3, paramIn4, paramOut5, paramOut6};
        setFunction(false);
        setParameters(paramArray);
    }

    public ApiManagementError run (ApiManagementError o) throws Exception {

        logger.debug("IN: " + inParamName1 + "-> " + o.getServicio());
        logger.debug("IN: " + inParamName2 + "-> " + o.getCodigoError());
        logger.debug("IN: " + inParamName3 + "-> " + o.getDescripcionError());
        logger.debug("IN: " + inParamName4 + "-> " + o.getCodigoHttp());

        Map spResult = this.execute(o.getServicio(), o.getCodigoError(), o.getDescripcionError(), o.getCodigoHttp());

        logger.info("OUT: " + outParamName1 + " -> " + spResult.get(outParamName1));
        logger.info("OUT: " + outParamName2 + " -> " + spResult.get(outParamName2));

        o.setCodigoError(String.valueOf(spResult.get(outParamName1)));
        o.setDescripcionError(String.valueOf(spResult.get(outParamName2)));

        return o;
    }
}