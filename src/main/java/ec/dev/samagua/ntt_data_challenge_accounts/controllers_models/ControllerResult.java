package ec.dev.samagua.ntt_data_challenge_accounts.controllers_models;

import ec.dev.samagua.ntt_data_challenge_accounts.services_models.ServiceResultError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ControllerResult<E> {
    private String status;
    private E data;
    private ControllerResultError error;

    public static <E> ControllerResult<E> getSuccessResult(E data) {
        return ControllerResult.<E>builder()
                .status("success")
                .data(data)
                .build();
    }

    public static <E> ControllerResult<E> getErrorResult(String code, String message, List<ServiceResultError> serviceErrors) {
        List<ControllerResultErrorDetail> details = serviceErrors.stream().map(error -> ControllerResultErrorDetail.builder()
                        .code(error.getCode())
                        .message(error.getMessage())
                        .field(error.getField())
                        .value(error.getValue())
                        .build())
                .toList();

        ControllerResultError error = ControllerResultError.builder()
                .code(code)
                .message(message)
                .details(details)
                .build();

        return ControllerResult.<E>builder()
                .status("error")
                .error(error)
                .build();
    }
}
