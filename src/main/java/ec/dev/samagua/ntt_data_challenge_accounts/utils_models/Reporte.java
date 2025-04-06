package ec.dev.samagua.ntt_data_challenge_accounts.utils_models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte {
    private String reporte;
    private Map<String, Object> parametros;

    private String nombre;
    private String tipoMime;
    private String bytesAsBase64;


    public void updateBytesAsBase64() {
        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(new Object()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(this.reporte, this.parametros, dataSource);
            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            this.bytesAsBase64 = Base64.getEncoder().encodeToString(bytes);
        } catch (JRException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

}
