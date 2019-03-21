package pe.com.entel.aplicacion.carrierbilling.mapper;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import pe.com.entel.aplicacion.carrierbilling.domain.Suscripcion;

import java.sql.ResultSet;
import java.sql.SQLException;

import static pe.com.entel.aplicacion.carrierbilling.constants.RegCobroPendienteConstants.*;

public class ProcesoCobroRowMapper implements RowMapper<Suscripcion> {

    static Logger logger = Logger.getLogger(ProcesoCobroRowMapper.class);

    @Override
    public Suscripcion mapRow(ResultSet rs, int i) throws SQLException {

        Suscripcion s = new Suscripcion();

        if (rs.getInt(CUR_NUMIDBILLCONTROL) != 0) {
            s.setBillControl(rs.getInt(CUR_NUMIDBILLCONTROL));
        }

        if (rs.getInt(CUR_NUMIDSUSCRIPCION) != 0) {
            s.setIdSuscripcion(rs.getInt(CUR_NUMIDSUSCRIPCION));
        }

        s.setMontoCobro(rs.getDouble(CUR_NUMMONTOCOBRO));


        if (rs.getString(CUR_VCHTELEFONO) != null) {
            s.setTelefono(rs.getString(CUR_VCHTELEFONO));
        }


        if (rs.getInt(CUR_NUMIDOFERTA) != 0) {
            s.setIdOferta(rs.getInt(CUR_NUMIDOFERTA));
        }

        if (rs.getInt(CUR_NUMCOID) != 0) {
            s.setCoId(rs.getInt(CUR_NUMCOID));
        }

        if (rs.getString(CUR_VCHIDCLIENTE) != null) {
            s.setIdCliente(rs.getString(CUR_VCHIDCLIENTE));
        }

        if (rs.getString(CUR_VCHCURRENCY) != null) {
            s.setCurrency(rs.getString(CUR_VCHCURRENCY));
        }

        if (rs.getString(CUR_VCHMERCHANT_ACCOUNT_KEY) != null) {
            s.setMerchantAccountKey(rs.getString(CUR_VCHMERCHANT_ACCOUNT_KEY));
        }

        if (rs.getString(CUR_VCHPRODUCT_KEY) != null) {
            s.setProductKey(rs.getString(CUR_VCHPRODUCT_KEY));
        }

        if (rs.getString(CUR_VCHPRODUCT_DESCRIPTION) != null) {
            s.setProductDescription(rs.getString(CUR_VCHPRODUCT_DESCRIPTION));
        }

        if (rs.getString(CUR_VCHPRODUCT_CATEGORY) != null) {
            s.setProductCategory(rs.getString(CUR_VCHPRODUCT_CATEGORY));
        }

        if (rs.getString(CUR_VCHMERCHANT_TRANSACTION_ID) != null) {
            s.setMerchantTransactionId(rs.getString(CUR_VCHMERCHANT_TRANSACTION_ID));
        }

        if (rs.getString(CUR_VCHNOMBRE_CANAL) != null) {
            s.setCanal(rs.getString(CUR_VCHNOMBRE_CANAL));
        }

        if (rs.getString(CUR_VCHREQUESTID) != null) {
            s.setRequestId(rs.getString(CUR_VCHREQUESTID));
        }

        if (rs.getString(CUR_VCHBANGOTRANSACTIONID) != null) {
            s.setBangoTransactionId(rs.getString(CUR_VCHBANGOTRANSACTIONID));
        }

        if (rs.getInt(CUR_NUMCANAL) != 0) {
            s.setIdCanal(rs.getInt(CUR_NUMCANAL));
        }

        if (rs.getString(CUR_VCHTIPOEJECUCION) != null) {
            s.setTipoEjecucion(rs.getString(CUR_VCHTIPOEJECUCION));
            logger.debug("CUR_VCHTIPOEJECUCION : " + rs.getString(CUR_VCHTIPOEJECUCION));
        }
        
        return s;
    }
}
