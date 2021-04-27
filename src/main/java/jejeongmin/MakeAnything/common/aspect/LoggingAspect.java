package jejeongmin.MakeAnything.common.aspect;

import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
@Aspect
@Slf4j
@SuppressWarnings("unchecked")
public class LoggingAspect {

    @Pointcut("@annotation(jejeongmin.MakeAnything.common.annotation.AutoLogging)")
    public void logging() {}

    @Pointcut("@annotation(jejeongmin.MakeAnything.common.annotation.AutoLoggingWithUser)")
    public void loggingWithUser() {}

    @Around("logging()")
    public Response methodLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        ResponseData<Object> response = (ResponseData<Object>) joinPoint.proceed();
        printLogInfo(joinPoint, response,false);
        return response;
    }

    @Around("loggingWithUser()")
    public Response methodLoggingWithUser(ProceedingJoinPoint joinPoint) throws Throwable {
        ResponseData<Object> response = (ResponseData<Object>) joinPoint.proceed();
        printLogInfo(joinPoint, response,true);
        return response;
    }

    private void printLogInfo(ProceedingJoinPoint joinPoint, ResponseData<Object> response, boolean withUser) throws Throwable {
        Map<String, Object> params = new HashMap<>();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        params.put("Controller", joinPoint.getSignature().getDeclaringType().getSimpleName());
        params.put("Method", "/"+joinPoint.getSignature().getName());
        params.put("Request", getParams(request));
        params.put("Data", response.getDataInfo());
        params.put("Time", new Date());
        if (withUser) {
            params.put("User", ((User) request.getAttribute("user")).getName());
        }
        log.info("AutoLogging {}", params);
    }

    private static JSONObject getParams(HttpServletRequest request) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String param = params.nextElement();
            String replaceParam = param.replaceAll("\\.", "-");
            jsonObject.put(replaceParam, request.getParameter(param));
        }
        return jsonObject;
    }

}
