package jejeongmin.MakeAnything.common.aspect;

import jejeongmin.MakeAnything.common.exception.AuthorizationException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class AuthorizationAspect {

    @Pointcut("@annotation(jejeongmin.MakeAnything.common.annotation.AuthorizationCheck)")
    public void authorizationCheck() {}

    @Before("authorizationCheck() && args(request)")
    public boolean methodAuthorizationCheck(HttpServletRequest request) throws AuthorizationException {
        if (request.getAttribute("user") == null) {
            throw new AuthorizationException("유저 정보가 없음");
        }
        return true;
    }

}
