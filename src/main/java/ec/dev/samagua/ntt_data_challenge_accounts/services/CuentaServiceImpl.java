package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.CuentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository repository;


    @Override
    public Mono<List<Cuenta>> findAll() {
        return repository.findAll();
    }
}
