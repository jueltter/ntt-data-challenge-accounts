package ec.dev.samagua.ntt_data_challenge_accounts.clients_repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.clients_models.Cliente;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_controllers_models.ControllerResult;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClienteRepository {
    private final WebClient webClient;

    public Mono<List<Cliente>> findByNombre(String nombre) {
        return webClient.get()
                .uri("/clientes?nombre={nombre}", nombre)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ControllerResult<List<Cliente>>>() {})
                .onErrorMap(RepositoryException::getReadException)
                .doOnError(e -> log.error("Error fetching client", e))
                .map(ControllerResult::getData);
    }
}
