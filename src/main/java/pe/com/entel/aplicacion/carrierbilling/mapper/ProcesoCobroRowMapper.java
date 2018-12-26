package pe.com.entel.aplicacion.carrierbilling.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcesoCobroRowMapper implements RowMapper<Suscripcion> {

    static Logger logger = Logger.getLogger(ProcesoCobroRowMapper.class);

    @Override
    public Suscripcion mapRow(ResultSet rs, int i) throws SQLException {

        Suscripcion s = new Suscripcion();

        if (rs.getInt("NUMIDSUSCRIPCION") != 0) {
            s.setIdSuscripcion(rs.getInt("NUMIDSUSCRIPCION"));
        }

        if (rs.getDouble("NUMMONTOCOBRO") != 0) {
            s.setMontoCobro(rs.getDouble("NUMMONTOCOBRO"));
        }

        if (rs.getString("VCHTELEFONO") != null) {
            s.setTelefono(rs.getString("VCHTELEFONO"));
        }

        if (rs.getInt("NUMIDBILLCONTROL") != 0) {
            s.setBillControl(rs.getInt("NUMIDBILLCONTROL"));
        }

        if (rs.getInt("NUMIDOFERTA") != 0) {
            s.setIdOferta(rs.getInt("NUMIDOFERTA"));
        }

        if (rs.getInt("NUMCOID") != 0) {
            s.setCoId(rs.getInt("NUMCOID"));
        }

        if (rs.getString("VCHIDCLIENTE") != null) {
            s.setIdCliente(rs.getString("VCHIDCLIENTE"));
        }

        if (rs.getString("VCHCURRENCY") != null) {
            s.setCurrency(rs.getString("VCHCURRENCY"));
        }

        if (rs.getString("VCHMERCHANT_ACCOUNT_KEY") != null) {
            s.setMerchantAccountKey(rs.getString("VCHMERCHANT_ACCOUNT_KEY"));
        }

        if (rs.getString("VCHPRODUCT_KEY") != null) {
            s.setProductKey(rs.getString("VCHPRODUCT_KEY"));
        }

        if (rs.getString("VCHPRODUCT_DESCRIPTION") != null) {
            s.setProductDescription(rs.getString("VCHPRODUCT_DESCRIPTION"));
        }

        if (rs.getString("VCHPRODUCT_CATEGORY") != null) {
            s.setProductCategory(rs.getString("VCHPRODUCT_CATEGORY"));
        }

        if (rs.getString("VCHMERCHANT_TRANSACTION_ID") != null) {
            s.setMerchantTransactionId(rs.getString("VCHMERCHANT_TRANSACTION_ID"));
        }

        if (rs.getString("VCHCANAL_DESCRIPCION") != null) {
            s.setCanal(rs.getString("VCHCANAL_DESCRIPCION"));
        }

        return s;
    }
}
