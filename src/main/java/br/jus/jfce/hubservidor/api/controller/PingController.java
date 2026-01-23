package br.jus.jfce.hubservidor.api.controller;

import br.jus.jfce.hubservidor.api.domain.PingStatus;
import br.jus.jfce.hubservidor.api.service.PingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PingController {

    private final PingService pingService;

    public PingController(PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping("/ping")
    public ResponseEntity<PingStatus> ping() {
        return ResponseEntity.ok(pingService.ping());
    }
}
