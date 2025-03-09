package groupe.isi.com.gestion.etablissement.exception;

import lombok.Data;

@Data
public class ErreurResponse {
    private String message;
    private long timestamp;

    public ErreurResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public ErreurResponse(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }
} 