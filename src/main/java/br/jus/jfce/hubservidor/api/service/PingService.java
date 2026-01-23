package br.jus.jfce.hubservidor.api.service;

import br.jus.jfce.hubservidor.api.domain.PingStatus;
import java.time.Instant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PingService {

    @Transactional(readOnly = true)
    public PingStatus ping() {
        return new PingStatus("OK", Instant.now());
    }
}
