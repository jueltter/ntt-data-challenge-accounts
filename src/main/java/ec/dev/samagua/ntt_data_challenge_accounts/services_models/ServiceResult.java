package ec.dev.samagua.ntt_data_challenge_accounts.services_models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceResult<E> {
    private E value;
    private List<ServiceResultError> errors;

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
