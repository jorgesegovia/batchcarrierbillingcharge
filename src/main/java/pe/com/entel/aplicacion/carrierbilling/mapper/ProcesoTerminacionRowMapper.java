package pe.com.entel.aplicacion.carrierbilling.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;

public class ProcesoTerminacionRowMapper implements RowMapper<Suscripcion>{

    static Logger logger = Logger.getLogger(ProcesoTerminacionRowMapper.class);

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

        if (rs.getString("vchnombre") != null) {
            s.setCanal(rs.getString("vchnombre"));
        }
        
        return s;
    }
}

