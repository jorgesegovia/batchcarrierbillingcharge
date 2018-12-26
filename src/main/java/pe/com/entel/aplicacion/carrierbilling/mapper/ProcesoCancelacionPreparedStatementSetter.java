package pe.com.entel.aplicacion.carrierbilling.mapper;

import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProcesoCancelacionPreparedStatementSetter implements PreparedStatementSetter{

    static Logger logger = Logger.getLogger(ProcesoCancelacionPreparedStatementSetter.class);

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
        CallableStatement cs = (CallableStatement) ps;
        cs.registerOutParameter(1, OracleTypes.CURSOR);
        cs.registerOutParameter(2, OracleTypes.VARCHAR);
        cs.registerOutParameter(3, OracleTypes.VARCHAR);
    }
}
