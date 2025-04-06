package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.MovimientoCuentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoCuentaServiceImpl implements MovimientoCuentaService {

    private final MovimientoCuentaRepository repository;

    @Override
    public Mono<List<MovimientoCuenta>> findAll() {
        return repository.findAll();
    }
}
