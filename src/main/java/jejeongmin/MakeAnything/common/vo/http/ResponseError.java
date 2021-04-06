package jejeongmin.MakeAnything.common.vo.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseError extends Response {
    private final String error;

    public ResponseError(HttpStatus status, String message, String error) {
        super(status, message);
        this.error = error;
    }
}
