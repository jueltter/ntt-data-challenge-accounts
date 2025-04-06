package ec.dev.samagua.ntt_data_challenge_accounts.models;

import ec.dev.samagua.ntt_data_challenge_accounts.clients_models.Cliente;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoCuenta {
    private String clienteId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Cliente cliente;
    private List<EstadoCuentaDetalle> detalles;

    public void agregarCuenta(Cuenta cuenta) {
        EstadoCuentaDetalle detalle = EstadoCuentaDetalle.builder()
                .cuenta(cuenta)
                .build();

        this.detalles.add(detalle);
    }

    public void agregarMovimientosACuenta(Long cuentaId, List<MovimientoCuenta> movimientos) {
        EstadoCuentaDetalle detalle = this.detalles.stream().filter(item -> item.getCuenta().getId().equals(cuentaId)).findFirst().get();
        detalle.setMovimientos(movimientos);
    }

    public Map<String, Object> generarParametros() {
        return Collections.emptyMap();
    }

}
