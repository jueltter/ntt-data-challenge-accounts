package ec.dev.samagua.ntt_data_challenge_accounts.dtos_mappers;

import ec.dev.samagua.ntt_data_challenge_accounts.dtos.MovimientoCuentaDto;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimientoCuentaDtoMapper {
    MovimientoCuentaDto entityToDto(MovimientoCuenta entity);

    MovimientoCuenta dtoToEntity(MovimientoCuentaDto dto);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "cuenta")
    @Mapping(ignore = true, target = "tipoMovimiento")
    MovimientoCuentaDto entityToDtoReport(MovimientoCuenta entity);
}
