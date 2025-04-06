package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MovimientoCuentaService {
    Mono<List<MovimientoCuenta>> findAll();
}
