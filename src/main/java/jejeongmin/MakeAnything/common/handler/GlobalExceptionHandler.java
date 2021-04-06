package jejeongmin.MakeAnything.common.handler;

import jejeongmin.MakeAnything.common.exception.EncryptException;
import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(EncryptException.class)
    public Response handleEncryptException(EncryptException e) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "EncryptException");
    }

}
