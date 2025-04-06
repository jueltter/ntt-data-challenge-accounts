package ec.dev.samagua.ntt_data_challenge_accounts.repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CuentaReactiveRepository extends ReactiveCrudRepository<Cuenta, Long> {
}
