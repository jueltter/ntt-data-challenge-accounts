package ec.dev.samagua.ntt_data_challenge_accounts.repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MovimientoCuentaRepository {
    private final MovimientoCuentaReactiveRepository repository;

    public Mono<List<MovimientoCuenta>> findAll() {
        return repository.findAll()
                .collectList()
                .onErrorMap(RepositoryException::getReadException)
                .doOnError(e -> log.error("Error finding all account movements",e));
    }

}
