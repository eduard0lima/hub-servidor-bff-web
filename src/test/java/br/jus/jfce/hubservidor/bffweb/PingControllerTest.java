package br.jus.jfce.hubservidor.bffweb;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.jus.jfce.hubservidor.bffweb.controller.PingController;
import br.jus.jfce.hubservidor.bffweb.domain.PingStatus;
import br.jus.jfce.hubservidor.bffweb.service.PingService;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import br.jus.jfce.hubservidor.bffweb.config.CorsConfig;
import br.jus.jfce.hubservidor.bffweb.config.SecurityConfig;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PingController.class)
@Import({TestJwtConfig.class, SecurityConfig.class, CorsConfig.class})
class PingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PingService pingService;

    @Test
    void shouldReturnPingStatus() throws Exception {
        Instant now = Instant.parse("2024-01-01T00:00:00Z");
        given(pingService.ping()).willReturn(new PingStatus("OK", now));

        mockMvc.perform(get("/api/v1/ping").with(jwt()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("OK"))
            .andExpect(jsonPath("$.timestamp").value("2024-01-01T00:00:00Z"));
    }
}
