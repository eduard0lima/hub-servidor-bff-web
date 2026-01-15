package br.jus.jfce.hubservidor.bffweb.client;

import br.jus.jfce.hubservidor.bffweb.shared.correlation.CorrelationIdPropagator;
import br.jus.jfce.hubservidor.bffweb.shared.error.IntegrationException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class CoreClientImpl implements CoreClient {

    private final RestClient restClient;

    public CoreClientImpl(RestClient.Builder builder, CoreClientProperties properties) {
        this.restClient = builder
            .baseUrl(properties.baseUrl())
            .requestInterceptor((request, body, execution) -> {
                HttpHeaders headers = request.getHeaders();
                CorrelationIdPropagator.apply(headers);
                return execution.execute(request, body);
            })
            .build();
    }

    @Override
    public String getCoreStatus() {
        try {
            return restClient.get()
                .uri("/api/v1/status")
                .retrieve()
                .body(String.class);
        } catch (RestClientException ex) {
            throw new IntegrationException("Erro ao comunicar com hub-servidor-core");
        }
    }
}
