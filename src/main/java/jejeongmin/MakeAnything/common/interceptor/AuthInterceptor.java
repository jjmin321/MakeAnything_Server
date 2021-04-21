package jejeongmin.MakeAnything.common.interceptor;

import jejeongmin.MakeAnything.common.lib.Jwt;
import jejeongmin.MakeAnything.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private Jwt jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String token = jwt.extract(request, "Bearer");

            if (!ObjectUtils.isEmpty(token)) {
                User user = jwt.validateToken(token);
                request.setAttribute("user", user);
            } else {
                if (request.getMethod().equals("OPTIONS")) {
                    return true;
                }
            }
            return true;
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }

}
