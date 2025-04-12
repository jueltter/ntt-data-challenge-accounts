package ec.dev.samagua.ntt_data_challenge_accounts.dtos_mappers;

import ec.dev.samagua.ntt_data_challenge_accounts.dtos.EstadoClienteCuentaMovimientoDto;
import ec.dev.samagua.ntt_data_challenge_accounts.models.EstadoClienteCuentaMovimiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoClienteCuentaMovimientoDtoMapper {
    EstadoClienteCuentaMovimientoDto modelToDto(EstadoClienteCuentaMovimiento model);
    EstadoClienteCuentaMovimiento dtoToModel(EstadoClienteCuentaMovimientoDto dto);
}
