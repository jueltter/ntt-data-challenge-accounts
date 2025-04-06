package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.clients_repositories.ClienteRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.models.EstadoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.CuentaRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.MovimientoCuentaRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.Reporte;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReporteServiceImpl implements ReporteService {
    private static final long INVALID_ID = -1L;

    private final CuentaRepository cuentaRepository;
    private final MovimientoCuentaRepository movimientoCuentaRepository;
    private final ClienteRepository clienteRepository;



    @Override
    public Mono<Reporte> generarReporteEstadoCuenta(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return null;
    }

    @Override
    public Mono<List<MovimientoCuenta>> generarEstadoCuenta(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        AtomicReference<EstadoCuenta> estadoCuentaRef = new AtomicReference<>(EstadoCuenta.builder()
                .clienteId(clienteId)
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .detalles(new ArrayList<>())
                .build());


        return  clienteRepository.findByNombreOrClienteId(null, clienteId).flatMap(clientes -> {
                    if (clientes.isEmpty())    {
                        return Mono.error(new IllegalArgumentException("Cliente no encontrado"));
                    }
                    else {
                        estadoCuentaRef.updateAndGet(estadoCuenta -> {
                            estadoCuenta.setCliente(clientes.stream().findFirst().get());
                            return estadoCuenta;
                        });

                    }

                    return cuentaRepository.findbyClienteId(clienteId);
                })

                .flatMapMany(Flux::fromIterable).flatMap(cuenta -> {

                            estadoCuentaRef.updateAndGet(estadoCuenta -> {
                                estadoCuenta.agregarCuenta(cuenta);
                                return estadoCuenta;
                            });

                            return movimientoCuentaRepository.findByCuentaAndFechaBetween(cuenta.getId(), fechaInicio, fechaFin);
                        }


                ).flatMap(movimientos -> {
                    AtomicReference<Long> cuentaIdRef = new AtomicReference<>(INVALID_ID);

                    movimientos.stream().findFirst().ifPresent(movimiento -> {
                        cuentaIdRef.set(movimiento.getCuenta());
                    });

                    Long cuentaId = cuentaIdRef.get();

                    if (cuentaId != INVALID_ID) {
                        estadoCuentaRef.updateAndGet(estadoCuenta -> {
                            estadoCuenta.agregarMovimientosACuenta(cuentaId, movimientos);
                            return estadoCuenta;
                        });

                    }


                    return Mono.just(movimientos);

                }).collectList().flatMap(lista -> {
                    EstadoCuenta estadoCuenta = estadoCuentaRef.get();
                    log.debug("estado de cuenta: {}", estadoCuenta);

                    List<MovimientoCuenta> movimientos = estadoCuenta.getDetalles().stream().flatMap(detalle -> {
                        detalle.getMovimientos().forEach(movimiento -> {
                            movimiento.setNombreCliente(estadoCuenta.getCliente().getNombre());
                            movimiento.setNumeroCuenta(detalle.getCuenta().getNumeroCuenta());
                            movimiento.setTipoCuenta(detalle.getCuenta().getTipoCuenta());
                            movimiento.setEstadoCuenta(detalle.getCuenta().getEstado());
                        });

                        return detalle.getMovimientos().stream();
                    }).sorted(Comparator.comparing(MovimientoCuenta::getFecha)).toList();

                    return Mono.just(movimientos);
                });
    }
}
