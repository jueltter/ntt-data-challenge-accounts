package ec.dev.samagua.ntt_data_challenge_accounts.models;

import ec.dev.samagua.ntt_data_challenge_accounts.clients_models.Cliente;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoCuentaDetalle {
    private Cuenta cuenta;
    private List<MovimientoCuenta> movimientos;
}
