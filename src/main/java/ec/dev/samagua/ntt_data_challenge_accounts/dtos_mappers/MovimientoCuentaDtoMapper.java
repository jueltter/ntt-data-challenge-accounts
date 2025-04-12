package ec.dev.samagua.ntt_data_challenge_accounts.dtos_mappers;

import ec.dev.samagua.ntt_data_challenge_accounts.dtos.MovimientoCuentaDto;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimientoCuentaDtoMapper {
    MovimientoCuentaDto entityToDto(MovimientoCuenta entity);

    MovimientoCuenta dtoToEntity(MovimientoCuentaDto dto);
}
