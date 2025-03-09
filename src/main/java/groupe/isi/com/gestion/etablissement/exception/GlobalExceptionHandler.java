package groupe.isi.com.gestion.etablissement.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErreurResponse> handleRuntimeException(RuntimeException ex) {
        ErreurResponse erreur = new ErreurResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(erreur);
    }
} 