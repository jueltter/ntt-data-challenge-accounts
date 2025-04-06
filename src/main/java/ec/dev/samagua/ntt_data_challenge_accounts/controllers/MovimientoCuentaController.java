package ec.dev.samagua.ntt_data_challenge_accounts.controllers;

import ec.dev.samagua.ntt_data_challenge_accounts.dtos.MovimientoCuentaDto;
import ec.dev.samagua.ntt_data_challenge_accounts.dtos_mappers.MovimientoCuentaDtoMapper;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.services.MovimientoCuentaService;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_controllers_models.ControllerResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MovimientoCuentaController {

    private final MovimientoCuentaService service;
    private final MovimientoCuentaDtoMapper mapper;

    @GetMapping("/movimientos")
    public Mono<ControllerResult<List<MovimientoCuentaDto>>> findAll() {
        Mono<List<MovimientoCuenta>> entities = service.findAll();
        return entities.map(obj -> ControllerResult.getSuccessResult(obj.stream().map(mapper::entityToDto).toList()));
    }

}
