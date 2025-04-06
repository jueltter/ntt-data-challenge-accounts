package ec.dev.samagua.ntt_data_challenge_accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;


@Table(name = "movimiento_cuenta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimientoCuenta {
    @Id
    @Column(value= "id")
    private Long id;

    @Column(value= "fecha")
    private LocalDate fecha;

    @Column(value= "tipo_movimiento")
    private String tipoMovimiento;

    @Column(value= "valor")
    private BigDecimal valor;

    @Column(value= "saldo")
    private BigDecimal saldo;

    @Column(value= "cuenta")
    private Long cuenta;

    @Transient
    private String numeroCuenta;
}
