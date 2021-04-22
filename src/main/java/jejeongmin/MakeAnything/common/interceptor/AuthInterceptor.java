package jejeongmin.MakeAnything.common.interceptor;

import jejeongmin.MakeAnything.common.lib.Jwt;
import jejeongmin.MakeAnything.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = jwt.extract(request, "Bearer");
        User user = jwt.validateToken(token);
        request.setAttribute("user", user);
        return true;
    }

}
