package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.Reporte;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

public interface ReporteService {
    Mono<Reporte> generarReporteEstadoCuenta(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    Mono<List<MovimientoCuenta>> generarEstadoCuenta(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
