package ec.dev.samagua.ntt_data_challenge_accounts.repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CuentaRepository {

    private final CuentaReactiveRepository repository;

    public Mono<Cuenta> findById(Long id) {
        return repository.findById(id);
    }

    public Mono<List<Cuenta>> findAll() {
        return repository.findAll().collectList();
    }

}
