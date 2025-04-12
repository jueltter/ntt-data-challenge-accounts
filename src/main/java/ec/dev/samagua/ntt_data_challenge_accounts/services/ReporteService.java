package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.models.EstadoClienteCuentaMovimiento;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

public interface ReporteService {
    Mono<List<EstadoClienteCuentaMovimiento>> generarEstadoCliente(String idCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
