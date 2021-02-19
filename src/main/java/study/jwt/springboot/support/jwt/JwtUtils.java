package study.jwt.springboot.support.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import study.jwt.springboot.support.exception.ErrCode;
import study.jwt.springboot.support.exception.VException;
import study.jwt.springboot.support.utils.JsonUtils;

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
        log.info("{}", JsonUtils.toJson(payload));
        Algorithm algorithm = transform(signAlg, secretKey);
        //生成器
        JWTCreator.Builder builder = JWT.create()
                .withJWTId(payload.getId())
                .withSubject(payload.getSubject())
                .withIssuer(payload.getIssuer())
                .withIssuedAt(payload.getIssuedAt())
                .withExpiresAt(payload.getExpiration());
        //
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
    public static void verifyJwt(String jwt) {
        verifyJwt(jwt, DEFAULT_ALGORITHM);
    }

    public static void verifyJwt(String jwt, SignAlg signAlg) {
        verifyJwt(jwt, signAlg, DEFAULT_SECRET_KEY);
    }

    public static void verifyJwt(String jwt, SignAlg signAlg, String secretKey) {
        Algorithm algorithm = transform(signAlg, secretKey);
        //验证器
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        try {
            verifier.verify(jwt);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (ex instanceof TokenExpiredException) {
                throw new VException(ErrCode.AUTH_RE_LOGIN);
            } else if (ex instanceof SignatureVerificationException) {
                throw new VException(ErrCode.AUTH_TOKEN_ERROR);
            } else {
                throw new VException(ErrCode.AUTH_TOKEN_EXPIRED_ERROR);
            }
        }
    }

    /**
     * 解析Jwt
     */
    public static Map<String, String> parseJwt(String jwt) {
        //解码器
        DecodedJWT decodedJWT = JWT.decode(jwt);
//        log.info(" header= {}", decodedJWT.getHeader());
//        log.info("payload= {}", decodedJWT.getPayload());
//        log.info("   sign= {}", decodedJWT.getSignature());
        //
        Map<String, String> claims = Maps.newHashMap();
        decodedJWT.getClaims().forEach((k, v) -> {
            claims.put(k, v.asString());
        });
//        log.info(" claims= {}", JsonUtils.toJson(claims));
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

    public enum VerifyRst {
        OK, TOKEN_EXPIRED, SIGN_ERROR, FAIL;
    }
}
