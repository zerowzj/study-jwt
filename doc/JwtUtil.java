package study.jwt.springboot.support.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class JwtUtil {

    private final static SignAlg DEFAULT_ALGORITHM = SignAlg.HS256;

    private final static String DEFAULT_SECRET_KEY = "abc!@#XYZ123";

    /**
     * 生成jwt
     */
    public static String createJwt(Map<String, String> claims) {
        return createJwt(claims, DEFAULT_ALGORITHM);
    }

    public static String createJwt(Map<String, String> claims, SignAlg signAlg) {
        return createJwt(claims, signAlg, DEFAULT_SECRET_KEY);
    }

    public static String createJwt(Map<String, String> claims, SignAlg signAlg, String secretKey) {
        SignatureAlgorithm algorithm = transform(signAlg);
//        SecretKey key = Keys.hmacShaKeyFor();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setSubject("subject")
//                .signWith()
                .signWith(algorithm, secretKey.getBytes());
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
        JwtParser parser = Jwts.parserBuilder()
                .build();
        Jws<Claims> jws = parser.parseClaimsJws(jwt);
        log.info("header= {}", jws.getHeader());
        log.info("body= {}", jws.getBody());
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