package ec.dev.samagua.ntt_data_challenge_accounts.utils_config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebClientConfig {
    @Value("${ntt-data-challenge-clients.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        log.debug("WebClientConfig: baseUrl = {}", baseUrl);
        return builder.baseUrl(baseUrl).build();
    }
}
