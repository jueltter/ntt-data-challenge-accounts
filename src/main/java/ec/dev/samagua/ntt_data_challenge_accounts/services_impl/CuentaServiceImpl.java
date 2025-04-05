package ec.dev.samagua.ntt_data_challenge_accounts.services_impl;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.CuentaRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.services.CuentaService;
import ec.dev.samagua.ntt_data_challenge_accounts.services_models.ServiceResult;
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
    public Mono<ServiceResult<List<Cuenta>>> findAll() {
        Mono<List<Cuenta>> entities = repository.findAll();

        return entities.map(obj -> new ServiceResult<>(obj, null));
    }
}
