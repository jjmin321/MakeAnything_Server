package jejeongmin.MakeAnything.common.lib;

import io.jsonwebtoken.*;
import jejeongmin.MakeAnything.common.enums.JwtEnum;
import jejeongmin.MakeAnything.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
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

}
