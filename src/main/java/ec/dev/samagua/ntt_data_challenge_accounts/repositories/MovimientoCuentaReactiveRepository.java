package ec.dev.samagua.ntt_data_challenge_accounts.repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MovimientoCuentaReactiveRepository extends ReactiveCrudRepository<MovimientoCuenta, Long>  {
}
