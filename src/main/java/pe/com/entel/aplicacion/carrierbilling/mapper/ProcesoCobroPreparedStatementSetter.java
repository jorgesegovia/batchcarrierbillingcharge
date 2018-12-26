package pe.com.entel.aplicacion.carrierbilling.mapper;

import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.math.BigDecimal;
import java.sql.*;

public class ProcesoCobroPreparedStatementSetter implements PreparedStatementSetter {

    static Logger logger = Logger.getLogger(ProcesoCobroPreparedStatementSetter.class);

    private int paginaActual;

    @Override
    public void setValues(PreparedStatement ps) throws SQLException {
        CallableStatement cs = (CallableStatement) ps;
        logger.debug(ps);
        logger.debug("Pagina Actual seteada: " + paginaActual);

        cs.setBigDecimal(1, new BigDecimal(paginaActual));
        cs.registerOutParameter(2, OracleTypes.CURSOR);
        cs.registerOutParameter(3, OracleTypes.VARCHAR);
        cs.registerOutParameter(4, OracleTypes.VARCHAR);
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getPaginaActual() {
        return paginaActual;
    }
}
