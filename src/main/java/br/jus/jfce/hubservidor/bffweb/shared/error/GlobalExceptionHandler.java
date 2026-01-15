package br.jus.jfce.hubservidor.bffweb.shared.error;

import br.jus.jfce.hubservidor.bffweb.shared.correlation.CorrelationIdFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex,
                                                             HttpServletRequest request) {
        List<String> details = ex.getBindingResult().getFieldErrors().stream()
            .map(this::formatFieldError)
            .collect(Collectors.toList());
        return buildResponse(HttpStatus.BAD_REQUEST, ApiErrorCode.VALIDATION_ERROR,
            "Validation failed", details, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(ConstraintViolationException ex,
                                                                      HttpServletRequest request) {
        List<String> details = ex.getConstraintViolations().stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .collect(Collectors.toList());
        return buildResponse(HttpStatus.BAD_REQUEST, ApiErrorCode.VALIDATION_ERROR,
            "Validation failed", details, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusiness(BusinessException ex, HttpServletRequest request) {
        HttpStatus status = ex.getStatus();
        return buildResponse(status, ApiErrorCode.BUSINESS_RULE, ex.getMessage(), List.of(), request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(NotFoundException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ApiErrorCode.NOT_FOUND, ex.getMessage(), List.of(), request);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthentication(AuthenticationException ex,
                                                                  HttpServletRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, ApiErrorCode.UNAUTHORIZED, "Unauthorized", List.of(), request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiErrorResponse> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.FORBIDDEN, ApiErrorCode.FORBIDDEN, "Forbidden", List.of(), request);
    }

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<ApiErrorResponse> handleIntegration(IntegrationException ex, HttpServletRequest request) {
        HttpStatus status = ex.getStatus();
        return buildResponse(status, ApiErrorCode.INTEGRATION_ERROR, ex.getMessage(), List.of(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex, HttpServletRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ApiErrorCode.INTERNAL_ERROR,
            "Unexpected error", List.of(), request);
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus status,
                                                           ApiErrorCode errorCode,
                                                           String message,
                                                           List<String> details,
                                                           HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(
            Instant.now(),
            request.getRequestURI(),
            status.value(),
            errorCode,
            message,
            details,
            MDC.get(CorrelationIdFilter.MDC_KEY)
        );
        String correlationId = MDC.get(CorrelationIdFilter.MDC_KEY);
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(status);
        if (correlationId != null && !correlationId.isBlank()) {
            builder.header(CorrelationIdFilter.CORRELATION_ID_HEADER, correlationId);
        }
        return builder.body(response);
    }

    private String formatFieldError(FieldError fieldError) {
        return fieldError.getField() + ": " + fieldError.getDefaultMessage();
    }
}
