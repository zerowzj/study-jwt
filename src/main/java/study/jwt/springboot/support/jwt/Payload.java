package study.jwt.springboot.support.jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Payload {
    /* 编号 */
    public static final String ID = "jti";
    /* 主题 */
    public static final String SUBJECT = "sub";
    /* 签发人 */
    public static final String ISSUER = "iss";
    /* 签发时间 */
    public static final String ISSUED_AT = "iat";
    /* 过期时间 */
    public static final String EXPIRATION = "exp";
    /* 生效时间 */
    public static final String NOT_BEFORE = "nbf";
    /*  */
    public static final String AUDIENCE = "aud";

    private Map<String, Object> data = new HashMap<>();

    @Setter
    @Getter
    private Map<String, String> claims = new HashMap<>();

    public Payload setId(String jti) {
        data.put(ID, jti);
        return this;
    }

    public String getId() {
        return (String) data.get(ID);
    }

    public Payload setSubject(String sub) {
        data.put(SUBJECT, sub);
        return this;
    }

    public String getSubject() {
        return (String) data.get(SUBJECT);
    }

    public Payload setIssuer(String iss) {
        data.put(ISSUER, iss);
        return this;
    }

    public String getIssuer() {
        return (String) data.get(ISSUER);
    }

    public Payload setIssuedAt(Date iat) {
        data.put(ISSUED_AT, iat);
        return this;
    }

    public Date getIssuedAt() {
        return (Date) data.get(ISSUED_AT);
    }

    public Payload setExpiration(Date exp) {
        data.put(EXPIRATION, exp);
        return this;
    }

    public Date getExpiration() {
        return (Date) data.get(EXPIRATION);
    }

    public Payload setNotBefore(Date nbf) {
        data.put(NOT_BEFORE, nbf);
        return this;
    }

    public Date getNotBefore() {
        return (Date) data.get(NOT_BEFORE);
    }
}
