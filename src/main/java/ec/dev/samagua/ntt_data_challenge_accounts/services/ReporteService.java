package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.Reporte;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ReporteService {
    Mono<Reporte> generarEstadoCuenta(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
