package jejeongmin.MakeAnything.common.lib;

import jejeongmin.MakeAnything.user.domain.entity.User;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class Jwt {



    /**
     * @param user - An User want to create Token
     * @return token - A Token created with user information
     */

    public String createToken(User user, JwtEnum) {
        Date now = new Date();
        Calendar expiredAt = Calendar.getInstance();
        expiredAt.setTime(now);
        String secretKey = "";
        expiredAt.add(Calendar.HOUR, 1);
        secr
    }

}
