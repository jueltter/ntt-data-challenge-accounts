package ec.dev.samagua.ntt_data_challenge_accounts.repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CuentaReactiveRepository extends ReactiveCrudRepository<Cuenta, Long> {
    Mono<Long> countByNumeroCuenta(String numeroCuenta);
    Mono<Cuenta> findByClienteId(String clienteId);
}
