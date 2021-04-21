package jejeongmin.MakeAnything.common.lib;

import io.jsonwebtoken.*;
import jejeongmin.MakeAnything.common.enums.JwtEnum;
import jejeongmin.MakeAnything.user.domain.entity.User;
import jejeongmin.MakeAnything.user.domain.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

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
        try {
            if (ObjectUtils.isEmpty(token)) {
                throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "권한 없음.");
            }

            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretAccessKey))
                    .parseClaimsJws(token).getBody();

            Optional<User> user = userRepository.findById((Integer) claims.get("id"));

            if (!user.isPresent()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "회원 없음");
            }

            return user.get();
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (ExpiredJwtException e) {
            throw new HttpClientErrorException(HttpStatus.GONE, "토큰 만료.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조.");
        } catch (IllegalArgumentException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰 없음.");
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    /**
     * 토큰 갱신
     * @param refreshToken - A RefreshToken get from client
     * @return accessToken - New AccessToken send to client
     */

    public String refresh(String refreshToken) {
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretRefreshKey))
                    .parseClaimsJws(refreshToken).getBody();

            Optional<User> user = userRepository.findById((Integer) claims.get("id"));

            if (!user.isPresent()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "회원 없음");
            }

            return createToken(user.get(), JwtEnum.ACCESS);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.GONE, "토큰 만료.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조.");
        } catch (IllegalArgumentException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰 없음.");
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
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
