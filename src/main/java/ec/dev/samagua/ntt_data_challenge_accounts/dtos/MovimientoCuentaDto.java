package ec.dev.samagua.ntt_data_challenge_accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoCuentaDto {
    // private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Long id;
    private LocalDateTime fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private BigDecimal saldo;
    private Long cuenta;
    private String numeroCuenta;
    private BigDecimal saldoAnterior;

    // attributes report
    private String nombreCliente;
    private String tipoCuenta;
    private String estadoCuenta;

    /*public String getFechaAsString() {
        if (getFecha() == null) {
            return null;
        }
        return getFecha().format(formatter);
    }*/
}
