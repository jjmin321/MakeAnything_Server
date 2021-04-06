package jejeongmin.MakeAnything.common.vo.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseData<T> extends Response{
    private final T data;

    public ResponseData(HttpStatus status, String message, T data) {
        super(status, message);
        this.data = data;
    }
}
