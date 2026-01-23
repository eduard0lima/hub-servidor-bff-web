package br.jus.jfce.hubservidor.api.shared.correlation;

import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;

public final class CorrelationIdPropagator {

    private CorrelationIdPropagator() {
    }

    public static void apply(HttpHeaders headers) {
        String correlationId = MDC.get(CorrelationIdFilter.MDC_KEY);
        if (correlationId != null && !correlationId.isBlank()) {
            headers.set(CorrelationIdFilter.CORRELATION_ID_HEADER, correlationId);
        }
    }
}
