package jejeongmin.MakeAnything.common.aspect;

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
public class LoggingAspect {

    @Pointcut("@annotation(jejeongmin.MakeAnything.common.annotation.AutoLogging)")
    public void autoLogging() {}

    @Around("autoLogging()")
    public Object methodLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        Map<String, Object> params = new HashMap<>();
        params.put("User", ((User) request.getAttribute("user")).getName());
        params.put("Controller", joinPoint.getSignature().getDeclaringType().getSimpleName());
        params.put("Method", "/"+joinPoint.getSignature().getName());
        params.put("Params", getParams(request));
        params.put("Time", new Date());
        log.info("AutoLogging {}", params);
        return result;
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
