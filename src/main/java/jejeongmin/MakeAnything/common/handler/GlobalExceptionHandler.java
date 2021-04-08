package jejeongmin.MakeAnything.common.handler;

import jejeongmin.MakeAnything.common.exception.EncryptException;
import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * All Methods
     * @param e - Exception Information
     * @return - ResponseError with Status Code, Exception Message, Exception Kind
     */

    @ExceptionHandler(EncryptException.class)
    public Response handleEncryptException(EncryptException e) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "EncryptException");
    }

    @ExceptionHandler(NullPointerException.class)
    public Response handleNullPointerException(NullPointerException e) {
        e.printStackTrace();
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "NullPointerException");
    }

}
