package ec.dev.samagua.ntt_data_challenge_accounts.repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface MovimientoCuentaReactiveRepository extends ReactiveCrudRepository<MovimientoCuenta, Long>  {
    Mono<Long> countByCuenta(Long cuenta);
    Flux<MovimientoCuenta> findByCuentaAndFechaBetween(Long cuenta, LocalDate fechaInicial, LocalDate fechaFinal);
    Flux<MovimientoCuenta> findByCuenta(Long cuenta);
}
