package ec.dev.samagua.ntt_data_challenge_accounts.controllers;


import ec.dev.samagua.ntt_data_challenge_accounts.controllers_models.ControllerResult;
import ec.dev.samagua.ntt_data_challenge_accounts.dtos.CuentaDto;
import ec.dev.samagua.ntt_data_challenge_accounts.dtos_mappers.CuentaDtoMapper;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.services.CuentaService;
import ec.dev.samagua.ntt_data_challenge_accounts.services_models.ServiceResult;
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
public class CuentaController {

    private final CuentaService service;
    private final CuentaDtoMapper mapper;

    @GetMapping("/cuentas")
    public Mono<ControllerResult<List<CuentaDto>>> findAll() {
        Mono<ServiceResult<List<Cuenta>>> entities = service.findAll();
        return entities.map(obj -> {
            if (obj.hasErrors()) {
                return ControllerResult.getErrorResult("1234", "An error has occurred while searching accounts", obj.getErrors());
            }
            return ControllerResult.getSuccessResult(obj.getValue().stream().map(mapper::entityToDto).toList());
        });
    }

}
