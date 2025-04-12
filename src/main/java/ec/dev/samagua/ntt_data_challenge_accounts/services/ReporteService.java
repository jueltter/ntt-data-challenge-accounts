package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.models.EstadoClienteCuentaMovimiento;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.Reporte;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

public interface ReporteService {
    Mono<Reporte> generarReporteEstadoCuenta(String idCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    Mono<List<EstadoClienteCuentaMovimiento>> generarEstadoCliente(String idCliente, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
