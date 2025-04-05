package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.services_models.ServiceResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CuentaService {
    Mono<ServiceResult<List<Cuenta>>> findAll();

}
