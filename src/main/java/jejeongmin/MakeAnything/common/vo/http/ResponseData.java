package jejeongmin.MakeAnything.common.vo.http;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseData<T> extends Response{

    private final T data;

    public ResponseData(HttpStatus status, String message, T data) {
        super(status, message);
        this.data = data;
    }

    public String getDataInfo() {
        return ToStringBuilder.reflectionToString(data, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
