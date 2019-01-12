package pe.com.entel.aplicacion.carrierbilling.mapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;

import oracle.jdbc.OracleTypes;

public class ProcesoTerminacionPreparedStatementSetter implements PreparedStatementSetter{

    static Logger logger = Logger.getLogger(ProcesoTerminacionPreparedStatementSetter.class);

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
    	logger.debug("preparedStatement : " + ps);
        CallableStatement cs = (CallableStatement) ps;
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.registerOutParameter(2, OracleTypes.VARCHAR);
        cs.registerOutParameter(3, OracleTypes.VARCHAR);
    }
}