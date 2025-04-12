package ec.dev.samagua.ntt_data_challenge_accounts.models_mappers;

import ec.dev.samagua.ntt_data_challenge_accounts.clients_models.Cliente;
import ec.dev.samagua.ntt_data_challenge_accounts.models.EstadoCliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoClienteMapper {
    EstadoCliente entityToModel(Cliente cliente);

    Cliente modelToEntity(EstadoCliente estadoCliente);
}
