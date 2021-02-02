package study.jwt.springboot.support.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import study.jwt.springboot.support.utils.JsonUtils;

import javax.validation.constraints.AssertTrue;
import java.util.Map;

@Slf4j
public class JwtUtils {

    private final static SignAlg DEFAULT_ALGORITHM = SignAlg.HS256;

    private final static String DEFAULT_SECRET_KEY = "abc!@#XYZ123";

    /**
     * 生成jwt
     */
    public static String createJwt(Map<String, Object> claims) {
        return createJwt(claims, DEFAULT_ALGORITHM, DEFAULT_SECRET_KEY);
    }

    public static String createJwt(Map<String, Object> claims, SignAlg signAlg) {
        return createJwt(claims, signAlg, DEFAULT_SECRET_KEY);
    }

    public static String createJwt(Map<String, Object> claims, SignAlg signAlg, String secretKey) {
        SignatureAlgorithm algorithm = transform(signAlg);
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .signWith(algorithm, secretKey);
        String jwt = builder.compact();
        return jwt;
    }

    /**
     * 解析jwt
     */
    public static Claims parseJwt(String jwt) {
        return parseJwt(jwt, DEFAULT_SECRET_KEY);
    }

    public static Claims parseJwt(String jwt, String secretKey) {
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt);
        log.info(">>>>>> {}", JsonUtils.toJson(jws.getHeader()));
        log.info(">>>>>> {}", jws.getSignature());
        log.info(">>>>>> {}", jws.getBody());
        return jws.getBody();
    }

    /**
     * 验证jwt
     */
    public static boolean verifyJwt(String jwt) {
        return verifyJwt(jwt, DEFAULT_ALGORITHM, DEFAULT_SECRET_KEY);
    }

    public static boolean verifyJwt(String jwt, SignAlg signAlg) {
        return verifyJwt(jwt, signAlg, DEFAULT_SECRET_KEY);
    }

    public static boolean verifyJwt(String jwt, SignAlg signAlg, String secretKey) {
        SignatureAlgorithm algorithm = transform(signAlg);
        if (secretKey == null) {
            secretKey = DEFAULT_SECRET_KEY;
        }

        return true;
    }

    private static SignatureAlgorithm transform(SignAlg signAlg) {
        if (signAlg == null) {
            signAlg = DEFAULT_ALGORITHM;
        }
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
