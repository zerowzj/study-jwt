package study.jwt.springboot.support.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;

@Slf4j
public class JwtUtil {

    private final static SignAlg DEFAULT_ALGORITHM = SignAlg.HS256;

    /**
     * 生成jwt
     */
    public static String createJwt(Payload payload) {
        return createJwt(payload, DEFAULT_ALGORITHM);
    }

    public static String createJwt(Payload payload, SignAlg signAlg) {
        SignatureAlgorithm algorithm = transform(signAlg);
        Key key = Keys.secretKeyFor(algorithm);
        JwtBuilder builder = Jwts.builder()
                .setId(payload.getId())
                .setSubject(payload.getSubject())
                .setIssuer(payload.getIssuer())
                .setIssuedAt(payload.getIssuedAt())
                .signWith(key);
        String jwt = builder.compact();
        return jwt;
    }

    /**
     * 解析jwt
     */
    public static Claims parseJwt(String jwt) {
        return parseJwt(jwt, DEFAULT_ALGORITHM);
    }

    public static Claims parseJwt(String jwt, SignAlg signAlg) {
        SignatureAlgorithm algorithm = transform(signAlg);
        Key key = Keys.secretKeyFor(algorithm);
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        Jws<Claims> jws = parser.parseClaimsJws(jwt);
        log.info("header= {}", jws.getHeader());
        log.info("body= {}", jws.getBody());

        return jws.getBody();
    }

    private static SignatureAlgorithm transform(SignAlg signAlg) {
        SignatureAlgorithm algorithm;
        switch (signAlg) {
            case HS256:
                algorithm = SignatureAlgorithm.HS256;
                break;
            case HS512:
                algorithm = SignatureAlgorithm.HS512;
                break;
            default:
                throw new RuntimeException("unsupported algorithm");
        }
        return algorithm;
    }
}
