package jejeongmin.MakeAnything.common.lib;

import io.jsonwebtoken.*;
import jejeongmin.MakeAnything.common.enums.JwtEnum;
import jejeongmin.MakeAnything.common.exception.AuthorizationException;
import jejeongmin.MakeAnything.user.domain.entity.User;
import jejeongmin.MakeAnything.user.domain.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;


@Component
public class Jwt {

    @Value("${jwt.secret.access}")
    private String secretAccessKey;

    @Value("${jwt.secret.refresh}")
    private String secretRefreshKey;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param user - An User want to create Token
     * @return token - A Token created with user information
     */

    public String createToken(User user, JwtEnum jwtEnum) {
        Date now = new Date();
        Calendar expiredAt = Calendar.getInstance();
        expiredAt.setTime(now);

        String secretKey = "";

        switch (jwtEnum) {
            case ACCESS:
                expiredAt.add(Calendar.HOUR, 1);
                secretKey = secretAccessKey;
                break;
            case REFRESH:
                expiredAt.add(Calendar.DATE, 30);
                secretKey = secretRefreshKey;
                break;
        }

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> claimsMap = new HashMap<String, Object>();

        claimsMap.put("id", user.getId());

        JwtBuilder builder = Jwts.builder().setHeaderParams(headerMap)
                .setClaims(claimsMap)
                .setExpiration(expiredAt.getTime())
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    /**
     * 토큰 검증
     * @param token - A AccessToken get from client
     * @return User - A Client User
     */

    public User validateToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretAccessKey))
                .parseClaimsJws(token).getBody();
        Optional<User> user = userRepository.findById((String) claims.get("id"));
        return user.get();
    }

    /**
     * 토큰 갱신
     * @param refreshToken - A RefreshToken get from client
     * @return accessToken - New AccessToken send to client
     */

    public String refresh(String refreshToken) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretRefreshKey))
                .parseClaimsJws(refreshToken).getBody();
        Optional<User> user = userRepository.findById((String) claims.get("id"));
        if (!user.isPresent()) {
            throw new AuthorizationException("유저 정보가 없음");
        }
        return createToken(user.get(), JwtEnum.ACCESS);
    }

    /**
     * 토큰 추출
     * @param request - HttpServletRequest from client
     * @return token - A Token from request header
     */

    public String extract(HttpServletRequest request, String type) {
        Enumeration<String> headers = request.getHeaders("Authorization");
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length()).trim();
            }
        }

        return Strings.EMPTY;
    }

}
