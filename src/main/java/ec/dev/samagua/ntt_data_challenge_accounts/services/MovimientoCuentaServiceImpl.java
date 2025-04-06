package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.entities.MovimientoCuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.CuentaRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.MovimientoCuentaRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.InvalidDataException;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.DataValidationResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovimientoCuentaServiceImpl implements MovimientoCuentaService {

    private final MovimientoCuentaRepository repository;
    private final CuentaRepository cuentaRepository;

    @Override
    public Mono<List<MovimientoCuenta>> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<MovimientoCuenta> create(MovimientoCuenta movimientoCuenta) {
        return cuentaRepository.findByNumeroCuenta(movimientoCuenta.getNumeroCuenta()).flatMap(
                cuenta -> {

                    if (!cuenta.isValidId()) {
                        return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("numeroCuenta", "is invalid")));
                    }

                    movimientoCuenta.setCuenta(cuenta.getId());
                    return repository.findByCuenta(movimientoCuenta.getCuenta());
                }
        ).flatMap(movimientos -> {
            movimientos.sort(Comparator.comparing(MovimientoCuenta::getFecha).reversed());
            AtomicReference<BigDecimal> balanceRef = new AtomicReference<>(BigDecimal.ZERO);
            movimientos.stream().findFirst().ifPresent(movimiento -> {
                balanceRef.set(movimiento.getSaldo());
            });

            BigDecimal balance = balanceRef.get();

            DataValidationResult validationResult = movimientoCuenta.validateForCreating(balance);

            if (!validationResult.isValid()) {
                return Mono.error(InvalidDataException.getInstance(validationResult.getErrors()));
            }

            if (movimientoCuenta.getValor().compareTo(BigDecimal.ZERO) > 0) {
                balance = balance.add(movimientoCuenta.getValor());
            }
            else {
                balance = balance.subtract(movimientoCuenta.getValor().abs());
            }

            movimientoCuenta.setFecha(LocalDate.now());
            movimientoCuenta.setSaldo(balance);

            return repository.create(movimientoCuenta);
        });
    }

    @Override
    public Mono<MovimientoCuenta> update(Long id, MovimientoCuenta newData) {
        return repository.findById(id).flatMap(movimientoCuenta -> {
            if (!movimientoCuenta.isValidId()) {
                return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("id", "is invalid")));
            }

            return cuentaRepository.findByNumeroCuenta(movimientoCuenta.getNumeroCuenta());

        }).flatMap(
                cuenta -> {

                    if (!cuenta.isValidId()) {
                        return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("numeroCuenta", "is invalid")));
                    }

                    newData.setCuenta(cuenta.getId());
                    return repository.findByCuenta(newData.getCuenta());
                }
        ).flatMap(movimientos -> {
            movimientos.sort(Comparator.comparing(MovimientoCuenta::getFecha).reversed());
            AtomicReference<BigDecimal> balanceRef = new AtomicReference<>(BigDecimal.ZERO);
            movimientos.stream().findFirst().ifPresent(movimiento -> {
                balanceRef.set(movimiento.getSaldo());
            });

            BigDecimal balance = balanceRef.get();

            DataValidationResult validationResult = newData.validateForUpdating(balance);

            if (!validationResult.isValid()) {
                return Mono.error(InvalidDataException.getInstance(validationResult.getErrors()));
            }

            if (newData.getValor().compareTo(BigDecimal.ZERO) > 0) {
                balance = balance.add(newData.getValor());
            }
            else {
                balance = balance.subtract(newData.getValor().abs());
            }

            newData.setFecha(LocalDate.now());
            newData.setSaldo(balance);

            return repository.update(newData);
        });
    }

    @Override
    public Mono<MovimientoCuenta> patch(Long id, MovimientoCuenta newData) {
        return repository.findById(id).flatMap(movimientoCuenta -> {
            if (!movimientoCuenta.isValidId()) {
                return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("id", "is invalid")));
            }

            return cuentaRepository.findByNumeroCuenta(movimientoCuenta.getNumeroCuenta());

        }).flatMap(
                cuenta -> {

                    if (newData.getNumeroCuenta()!= null && !cuenta.isValidId()) {
                        return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("numeroCuenta", "is invalid")));
                    }

                    newData.setCuenta(cuenta.getId());
                    return repository.findByCuenta(newData.getCuenta());
                }
        ).flatMap(movimientos -> {
            movimientos.sort(Comparator.comparing(MovimientoCuenta::getFecha).reversed());
            AtomicReference<BigDecimal> balanceRef = new AtomicReference<>(BigDecimal.ZERO);
            movimientos.stream().findFirst().ifPresent(movimiento -> {
                balanceRef.set(movimiento.getSaldo());
            });

            BigDecimal balance = balanceRef.get();

            DataValidationResult validationResult = newData.validateForPatching(balance);

            if (!validationResult.isValid()) {
                return Mono.error(InvalidDataException.getInstance(validationResult.getErrors()));
            }

            if (newData.getValor().compareTo(BigDecimal.ZERO) > 0) {
                balance = balance.add(newData.getValor());
            }
            else {
                balance = balance.subtract(newData.getValor().abs());
            }

            newData.setFecha(LocalDate.now());
            newData.setSaldo(balance);

            return repository.update(newData);
        });
    }

    @Override
    public Mono<Void> delete(Long id) {
        return repository.findById(id).flatMap(movimientoCuenta -> {
            if (!movimientoCuenta.isValidId()) {
                return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("id", "is invalid")));
            }

            return repository.delete(movimientoCuenta);
        });
    }
}
