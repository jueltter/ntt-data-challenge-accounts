package ec.dev.samagua.ntt_data_challenge_accounts.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table(name = "cuenta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {
    @Id
    @Column(value= "id")
    private Long id;

    @Column(value= "numero_cuenta")
    private String numeroCuenta;

    @Column(value= "tipo_cuenta")
    private String tipoCuenta;

    @Column(value= "saldo_inicial")
    private BigDecimal saldoInicial;

    @Column(value= "estado")
    private String estado;
}
