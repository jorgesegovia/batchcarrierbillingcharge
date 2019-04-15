package pe.com.entel.aplicacion.carrierbilling.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import pe.com.entel.aplicacion.carrierbilling.domain.ActualizacionProgramada;

public class ProcesoActualizacionProgRowMapper implements RowMapper<ActualizacionProgramada>{

    static Logger logger = Logger.getLogger(ProcesoActualizacionProgRowMapper.class);

    @Override
    public ActualizacionProgramada mapRow(ResultSet rs, int i) throws SQLException {

        logger.debug("ResultSet = " + rs);
        logger.debug("i = " + i);

        ActualizacionProgramada s = new ActualizacionProgramada();

        if (rs.getInt("NUMIDSUSCRIPCIONACTPROG") != 0) {
            s.setIdSuscripcionActProg(rs.getInt("NUMIDSUSCRIPCIONACTPROG"));
        }

        if (rs.getString("VCHESTADO") != null) {
            s.setEstado(rs.getString("VCHESTADO"));
        }
        
        return s;
    }
}