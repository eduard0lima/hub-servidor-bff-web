# hub-servidor-bff-web

BFF REST do Hub do Servidor (JFCE) para o hub-servidor-web.

## Como rodar

### Perfil local (H2 em memória)

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### Perfil padrão (PostgreSQL)

```bash
./mvnw spring-boot:run
```

Se preferir, use o Maven instalado:

```bash
mvn spring-boot:run
```

### PostgreSQL via Docker (opcional)

```bash
docker run --name hub-servidor-postgres -e POSTGRES_DB=hub_servidor -e POSTGRES_USER=hub -e POSTGRES_PASSWORD=hub -p 5432:5432 -d postgres:16
```

## Configuração necessária

Atualize o `issuer-uri` do seu provedor OAuth2/JWT e os origins permitidos para CORS:

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://issuer.example.com/realms/hub

app:
  cors:
    allowed-origins:
      - "http://localhost:4200"
```

## Correlation-Id

- O header `X-Correlation-Id` é aceito em todas as requests.
- Se não enviado, o BFF gera um UUID.
- O valor é devolvido em respostas de sucesso e erro.

## Envelope de erro

O payload de erro segue o formato abaixo:

```json
{
  "timestamp": "2024-01-01T00:00:00Z",
  "path": "/api/v1/resource",
  "status": 400,
  "errorCode": "VALIDATION_ERROR",
  "message": "Validation failed",
  "details": ["campo: mensagem"],
  "correlationId": "..."
}
```
