package pe.com.entel.aplicacion.carrierbilling.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcesoCancelacionRowMapper implements RowMapper<Suscripcion>{

    static Logger logger = Logger.getLogger(ProcesoCancelacionRowMapper.class);

    @Override
    public Suscripcion mapRow(ResultSet rs, int i) throws SQLException {

        logger.debug("ResultSet = " + rs);
        logger.debug("i = " + i);

        Suscripcion s = new Suscripcion();

        if (rs.getInt("NUMIDSUSCRIPCION") != 0) {
            s.setIdSuscripcion(rs.getInt("NUMIDSUSCRIPCION"));
        }

        if (rs.getString("VCHIDCUENTACOMPARTIDA") != null) {
            s.setShareAccountId(rs.getString("VCHIDCUENTACOMPARTIDA"));
        }

        return s;
    }
}
