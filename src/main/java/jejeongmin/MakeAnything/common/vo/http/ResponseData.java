package jejeongmin.MakeAnything.common.vo.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseData extends Response{
    private final Object data;

    public ResponseData(HttpStatus status, String message, Object data) {
        super(status, message);
        this.data = data;
    }
}
