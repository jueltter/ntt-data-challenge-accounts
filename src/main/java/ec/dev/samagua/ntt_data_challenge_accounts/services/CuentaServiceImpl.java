package ec.dev.samagua.ntt_data_challenge_accounts.services;

import ec.dev.samagua.ntt_data_challenge_accounts.clients_repositories.ClienteRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.entities.Cuenta;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.CuentaRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.repositories.MovimientoCuentaRepository;
import ec.dev.samagua.ntt_data_challenge_accounts.utils.BeanCopyUtil;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.InvalidDataException;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.DataValidationResult;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_models.IdentityFieldWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
@Slf4j
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository repository;
    private final ClienteRepository clienteRepository;
    private final MovimientoCuentaRepository movimientoCuentaRepository;


    @Override
    public Mono<List<Cuenta>> search(String clienteId) {
        if (clienteId == null) {
            return repository.findAll();
        }
        return repository.findbyClienteId(clienteId);
    }

    @Override
    public Mono<Cuenta> create(Cuenta cuenta) {
        if (cuenta.getNombreCliente() == null || cuenta.getNombreCliente().isBlank()) {
            return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("nombreCliente", "is mandatory")));
        }

        return clienteRepository.findByNombreOrClienteId(cuenta.getNombreCliente(), null)
                .flatMap(clientes -> {
                    if (clientes.isEmpty()) {
                        return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("nombreCliente", "is invalid")));
                    }

                    cuenta.setClienteId(clientes.stream().findFirst().get().getClienteId());

                    return repository.countByClienteId(cuenta.getNumeroCuenta());


                }).flatMap(countNumeroCuenta -> {

                            DataValidationResult validationResult = cuenta.validateForCreating(countNumeroCuenta);

                            if (!validationResult.isValid()) {
                                return Mono.error(InvalidDataException.getInstance(validationResult.getErrors()));
                            }

                            return repository.create(cuenta);
                        }


                );
    }

    @Override
    public Mono<Cuenta> update(Long id, Cuenta newData) {
        AtomicReference<Cuenta> atomicEntity = new AtomicReference<>();

        return repository.findById(id)
                .flatMap(entity -> {
                    if (!entity.isValidId()) {
                        return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("id", "is invalid")));
                    }

                    atomicEntity.set(entity);

                    return clienteRepository.findByNombreOrClienteId(newData.getNombreCliente(), null)
                            .flatMap(clientes -> {
                                if (clientes.isEmpty()) {
                                    return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("nombreCliente", "is invalid")));
                                }

                                newData.setClienteId(clientes.stream().findFirst().get().getClienteId());

                                return repository.countByClienteId(newData.getNumeroCuenta());
                            });
                }).flatMap(countNumeroCuenta -> {

                    IdentityFieldWrapper numeroCuentaWrapper = new IdentityFieldWrapper(countNumeroCuenta, Objects.equals(atomicEntity.get().getNumeroCuenta(), newData.getNumeroCuenta()));

                    DataValidationResult validationResult = newData.validateForUpdating(numeroCuentaWrapper);

                    if (!validationResult.isValid()) {
                        return Mono.error(InvalidDataException.getInstance(validationResult.getErrors()));
                    }

                    newData.setId(id);

                    Cuenta entity = atomicEntity.updateAndGet(cuenta -> {
                        BeanUtils.copyProperties(newData, cuenta);
                        return cuenta;
                    });

                    return repository.update(entity);
                });
    }

    @Override
    public Mono<Cuenta> patch(Long id, Cuenta newData) {
        AtomicReference<Cuenta> atomicEntity = new AtomicReference<>();

        return repository.findById(id)
                .flatMap(entity -> {
                    if (!entity.isValidId()) {
                        return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("id", "is invalid")));
                    }

                    atomicEntity.set(entity);

                    return clienteRepository.findByNombreOrClienteId(newData.getNombreCliente(), null)

                            .flatMap(clientes -> {
                                if (newData.getNombreCliente() != null && clientes.isEmpty()) {
                                    return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("nombreCliente", "is invalid")));
                                }

                                clientes.stream().findFirst().ifPresent(cliente -> {
                                    newData.setClienteId(cliente.getClienteId());
                                });

                                return repository.countByClienteId(newData.getNumeroCuenta());
                            });
                }).flatMap(countNumeroCuenta -> {

                    IdentityFieldWrapper numeroCuentaWrapper = new IdentityFieldWrapper(countNumeroCuenta, Objects.equals(atomicEntity.get().getNumeroCuenta(), newData.getNumeroCuenta()));

                    DataValidationResult validationResult = newData.validateForPatching(numeroCuentaWrapper);

                    if (!validationResult.isValid()) {
                        return Mono.error(InvalidDataException.getInstance(validationResult.getErrors()));
                    }

                    newData.setId(id);

                    Cuenta entity = atomicEntity.updateAndGet(cuenta -> {
                        BeanCopyUtil.copyNonNullProperties(newData, cuenta);
                        return cuenta;
                    });

                    return repository.update(entity);
                });
    }

    @Override
    public Mono<Void> delete(Long id) {
        AtomicReference<Cuenta> atomicEntity = new AtomicReference<>();

        return repository.findById(id).flatMap(entity -> {
            if (!entity.isValidId()) {
                return Mono.error(InvalidDataException.getInstance(Collections.singletonMap("id", "is invalid")));
            }

            atomicEntity.set(entity);

            return movimientoCuentaRepository.countByCuenta(id);
        }).flatMap(countMovimientoCuenta -> {

            DataValidationResult validationResult = atomicEntity.get().validateForDeleting(countMovimientoCuenta);

            if (!validationResult.isValid()) {
                return Mono.error(InvalidDataException.getInstance(validationResult.getErrors()));
            }

            return repository.delete(atomicEntity.get());

                }

        );
    }
}
