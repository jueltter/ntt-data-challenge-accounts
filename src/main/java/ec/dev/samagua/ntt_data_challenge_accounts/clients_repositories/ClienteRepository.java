package ec.dev.samagua.ntt_data_challenge_accounts.clients_repositories;

import ec.dev.samagua.ntt_data_challenge_accounts.clients_models.Cliente;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_controllers_models.ControllerResult;
import ec.dev.samagua.ntt_data_challenge_accounts.utils_exceptions.RepositoryException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClienteRepository {
    private final WebClient webClient;

    public Mono<List<Cliente>> findByNombreOrClienteId(String nombre, String clienteId) {
        try {
            URIBuilder uriBuilder = new URIBuilder("/clientes");
            Map<String, String> params = new HashMap<>();
            params.put("nombre", nombre);
            params.put("cliente-id", clienteId);
            params.forEach((key, value) -> {
                if (value != null && !value.isBlank()) {
                    uriBuilder.addParameter(key, value);
                }
            });

            return webClient.get()
                    .uri(uriBuilder.build().toString())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<ControllerResult<List<Cliente>>>() {})
                    .onErrorMap(RepositoryException::getReadException)
                    .doOnError(e -> log.error("Error fetching client", e))
                    .map(ControllerResult::getData);

        } catch (URISyntaxException e) {
            return Mono.error(e);
        }
    }
}
