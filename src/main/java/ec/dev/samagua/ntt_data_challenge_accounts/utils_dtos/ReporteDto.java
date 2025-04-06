package ec.dev.samagua.ntt_data_challenge_accounts.utils_dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteDto {
    private String nombre;
    private String tipoMime;
    private String bytesAsBase64;
}
