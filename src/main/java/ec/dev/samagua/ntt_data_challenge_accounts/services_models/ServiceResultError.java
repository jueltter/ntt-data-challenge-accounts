package ec.dev.samagua.ntt_data_challenge_accounts.services_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResultError {
    private String code;
    private String message;
    private String field;
    private String value;
}
