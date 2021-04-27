package jejeongmin.MakeAnything.common.handler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jejeongmin.MakeAnything.common.exception.AuthorizationException;
import jejeongmin.MakeAnything.common.exception.EncryptException;
import jejeongmin.MakeAnything.common.exception.FileIsEmptyException;
import jejeongmin.MakeAnything.common.exception.MakeDirectoryException;
import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(EncryptException.class)
    public Response handleEncryptException(EncryptException e) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "EncryptException");
    }

    @ExceptionHandler(NullPointerException.class)
    public Response handleNullPointerException(NullPointerException e) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), "NullPointerException");
    }

    @ExceptionHandler(AuthorizationException.class)
    public Response handleAuthorizationException(AuthorizationException e) {
        return new ResponseError(HttpStatus.UNAUTHORIZED, e.getMessage(), "AuthorizationException");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public Response handleExpiredJwtException(ExpiredJwtException e) {
        return new ResponseError(HttpStatus.FORBIDDEN, "JWT 토큰이 만료되어 요청을 수행할 수 없습니다", "ExpiredJwtException");
    }

    @ExceptionHandler(SignatureException.class)
    public Response handleSignatureException(SignatureException e) {
        return new ResponseError(HttpStatus.FORBIDDEN, "JWT 토큰이 위조되어 요청을 수행할 수 없습니다", "SignatureException");
    }

    @ExceptionHandler(MalformedJwtException.class)
    public Response handleMalformedJwtException(MalformedJwtException e) {
        return new ResponseError(HttpStatus.FORBIDDEN, "JWT 토큰이 위조되어 요청을 수행할 수 없습니다", "MalformedJwtException");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Response handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseError(HttpStatus.NOT_ACCEPTABLE, "JWT 토큰이 없어 요청을 수행할 수 없습니다", "IllegalArgumentException");
    }

    @ExceptionHandler(FileIsEmptyException.class)
    public Response handleFileIsEmptyException(FileIsEmptyException e) {
        return new ResponseError(HttpStatus.NOT_ACCEPTABLE, "파일이 제공되지 않아 요청을 수행할 수 없습니다", "FileIsEmptyException");
    }

    @ExceptionHandler(IOException.class)
    public Response handleIOException(IOException e) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, "파일을 읽는 과정에서 오류가 발생했습니다", "IOException");
    }

    @ExceptionHandler(MakeDirectoryException.class)
    public Response handleMakeDirectoryException(MakeDirectoryException e) {
        return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, "디렉토리 생성 과정에서 오류가 발생했습니다", "MakeDirectoryException");
    }

}
