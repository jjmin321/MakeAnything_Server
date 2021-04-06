package jejeongmin.MakeAnything.common.vo.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RealResponse {
    private final int status;
    private final String message;
    private Object data;

    public RealResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public RealResponse(HttpStatus status, String message, Object data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }

}
