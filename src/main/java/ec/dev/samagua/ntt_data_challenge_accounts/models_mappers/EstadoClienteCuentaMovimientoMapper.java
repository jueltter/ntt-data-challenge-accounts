package ec.dev.samagua.ntt_data_challenge_accounts.models_mappers;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.models.EstadoClienteCuentaMovimiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoClienteCuentaMovimientoMapper {
    EstadoClienteCuentaMovimiento entityToModel(MovimientoCuenta movimientoCuenta);
    MovimientoCuenta modelToEntity(EstadoClienteCuentaMovimiento estadoClienteCuentaMovimiento);
}
