package study.jwt.springboot.support.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public final class JwtUtils {

    private final static SignAlg DEFAULT_ALGORITHM = SignAlg.HS256;

    private final static String DEFAULT_SECRET_KEY = "abc!@#XYZ123";

    private JwtUtils() {
    }

    /**
     * 生成Jwt
     */
    public static String createJwt(Payload payload) {
        return createJwt(payload, DEFAULT_ALGORITHM);
    }

    public static String createJwt(Payload payload, SignAlg signAlg) {
        return createJwt(payload, signAlg, DEFAULT_SECRET_KEY);
    }

    public static String createJwt(Payload payload, SignAlg signAlg, String secretKey) {
        Algorithm algorithm = transform(signAlg, secretKey);
        //生成器
        JWTCreator.Builder builder = JWT.create()
                .withJWTId(payload.getId())
                .withSubject(payload.getSubject())
                .withExpiresAt(payload.getExpiresAt())
                .withNotBefore(payload.getNotBefore());
        Map<String, String> claims = payload.getClaims();
        if (claims != null) {
            claims.forEach((k, v) -> {
                builder.withClaim(k, v);
            });
        }
        String jwt = builder.sign(algorithm);
        return jwt;
    }

    /**
     * 验证Jwt
     */
    public static boolean verifyJwt(String jwt) {
        return verifyJwt(jwt, DEFAULT_ALGORITHM);
    }

    public static boolean verifyJwt(String jwt, SignAlg signAlg) {
        return verifyJwt(jwt, signAlg, DEFAULT_SECRET_KEY);
    }

    public static boolean verifyJwt(String jwt, SignAlg signAlg, String secretKey) {
        Algorithm algorithm = transform(signAlg, secretKey);
        //验证器
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        boolean isLegal = true;
        try {
            verifier.verify(jwt);
        } catch (Exception ex) {
            isLegal = false;
        }
        return isLegal;
    }

    /**
     * 解析Jwt
     */
    public static Map<String, String> parseJwt(String jwt) {
        //解码器
        DecodedJWT decodedJWT = JWT.decode(jwt);
        Map<String, String> claims = Maps.newHashMap();
        log.info("111111111111 {}", decodedJWT.getPayload());
        decodedJWT.getHeader();
        decodedJWT.getClaims().forEach((k, v) -> {
            claims.put(k, v.asString());
        });
        return claims;
    }

    private static Algorithm transform(SignAlg signAlg, String secretKey) {
        if (signAlg == null) {
            signAlg = DEFAULT_ALGORITHM;
        }
        if (secretKey == null) {
            secretKey = DEFAULT_SECRET_KEY;
        }
        Algorithm algorithm;
        switch (signAlg) {
            case HS256:
                algorithm = Algorithm.HMAC256(secretKey);
                break;
            case HS512:
                algorithm = Algorithm.HMAC512(secretKey);
                break;
            default:
                throw new IllegalArgumentException("不支持的算法");
        }
        return algorithm;
    }
}
