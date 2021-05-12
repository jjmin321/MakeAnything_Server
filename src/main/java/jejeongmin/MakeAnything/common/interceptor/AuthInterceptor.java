package jejeongmin.MakeAnything.common.interceptor;

import jejeongmin.MakeAnything.common.lib.Jwt;
import jejeongmin.MakeAnything.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (request.getMethod().equals("OPTIONS")) return true;

        String token = jwt.extract(request, "Bearer");
        User user = jwt.validateToken(token);
        request.setAttribute("user", user);
        return true;
    }

}
