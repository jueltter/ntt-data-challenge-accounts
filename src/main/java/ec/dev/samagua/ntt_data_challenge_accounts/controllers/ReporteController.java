package ec.dev.samagua.ntt_data_challenge_accounts.controllers;

import ec.dev.samagua.ntt_data_challenge_accounts.services.ReporteService;
import ec.dev.samagua.ntt_data_challenge_accounts.utils.CustomDateUtils;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_controllers_models.ControllerResult;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_dtos.ReporteDto;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_dtos_mappers.ReporteDtoMapper;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.DateRange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReporteController {
    private final ReporteService service;
    private final ReporteDtoMapper mapper;

    @GetMapping("/reportes")
    public Mono<ResponseEntity<ControllerResult<ReporteDto>>> generarEstadoCuenta(
            @RequestParam(name = "cliente-id", required = true) String clienteId,
            @RequestParam(name = "fecha", required = true) String rangoFechasAsString) {

        log.debug("executing GET /reportes, clienteId: {}, fecha: {}", clienteId, rangoFechasAsString);

        DateRange rangoFechas = CustomDateUtils.getDateRange(rangoFechasAsString);

        return service.generarEstadoCuenta(clienteId, rangoFechas.getStartDate(), rangoFechas.getEndDate())
                .flatMap(obj -> {
                    ControllerResult<ReporteDto> body = ControllerResult.getSuccessResult(mapper.entityToDto(obj));
                    return Mono.just(ResponseEntity.ok(body));
                })
                .doOnSuccess(obj -> log.debug("GET /reportes response: {}", obj));
    }
}
