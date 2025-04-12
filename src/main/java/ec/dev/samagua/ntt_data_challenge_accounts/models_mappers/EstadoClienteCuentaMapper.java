package ec.dev.samagua.ntt_data_challenge_accounts.models_mappers;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.models.EstadoClienteCuenta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoClienteCuentaMapper {
    EstadoClienteCuenta entityToModel(Cuenta cuenta);
    Cuenta modelToEntity(EstadoClienteCuenta estadoClienteCuenta);
}
