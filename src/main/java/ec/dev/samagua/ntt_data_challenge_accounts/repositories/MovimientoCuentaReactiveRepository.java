package ec.dev.samagua.ntt_data_challenge_accounts.repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoCuentaReactiveRepository extends ReactiveCrudRepository<MovimientoCuenta, Long>  {
    Mono<Long> countByCuenta(Long cuenta);
    Mono<List<MovimientoCuenta>> findByCuentaAndFechaBetween(Long cuenta, LocalDate fechaInicial, LocalDate fechaFinal);
    Mono<List<MovimientoCuenta>> findByCuenta(Long cuenta);
}
