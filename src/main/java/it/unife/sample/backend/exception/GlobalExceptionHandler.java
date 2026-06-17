package it.unife.sample.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Questo metodo intercetta TUTTE le IllegalArgumentException lanciate dai tuoi Service
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value()); // Stato 400 invece di 500
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage()); // Qui ci sarà il tuo testo: "Le date inserite non sono coerenti" ecc.

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}
