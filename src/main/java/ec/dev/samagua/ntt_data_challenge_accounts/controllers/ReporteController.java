package ec.dev.samagua.ntt_data_challenge_accounts.controllers;

import ec.dev.samagua.ntt_data_challenge_accounts.dtos.MovimientoCuentaDto;
import ec.dev.samagua.ntt_data_challenge_accounts.dtos_mappers.MovimientoCuentaDtoMapper;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.services.ReporteService;
import ec.dev.samagua.ntt_data_challenge_accounts.utils.CustomDateUtils;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_controllers_models.ControllerResult;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.DateRange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReporteController {
    private final ReporteService service;
    private final MovimientoCuentaDtoMapper mapper;

    @GetMapping("/reportes")
    public Mono<ResponseEntity<ControllerResult<List<MovimientoCuentaDto>>>> generarEstadoCuenta(
            @RequestParam(name = "cliente-id", required = true) String clienteId,
            @RequestParam(name = "fecha", required = true) String rangoFechasAsString) {

        log.debug("executing GET /reportes, clienteId: {}, fecha: {}", clienteId, rangoFechasAsString);

        DateRange rangoFechas = CustomDateUtils.getDateRange(rangoFechasAsString);

        Mono<List<MovimientoCuenta>> entities = service.generarEstadoCuenta(clienteId, rangoFechas.getStartDate(), rangoFechas.getEndDate());
        return entities.map(obj -> {
            ControllerResult<List<MovimientoCuentaDto>> body = ControllerResult.getSuccessResult(obj.stream()
                    .map(mapper::entityToDtoReport)
                    .toList());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(body);
        }).doOnSuccess(obj -> log.debug("GET /reportes response: {}", obj));
    }
}
