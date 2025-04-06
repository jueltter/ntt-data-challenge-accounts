package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CuentaService {
    Mono<List<Cuenta>> search(String clienteId);
    Mono<Cuenta> create(Cuenta cuenta);
    Mono<Cuenta> update(Long id, Cuenta newData);
    Mono<Cuenta> patch(Long id, Cuenta newData);
    Mono<Void> delete(Long id);

}
