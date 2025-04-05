package ec.dev.samagua.ntt_data_challenge_accounts.controllers_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ControllerResultErrorDetail {
    private String code;
    private String message;
    private String field;
    private String value;

}
