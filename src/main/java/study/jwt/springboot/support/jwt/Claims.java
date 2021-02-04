package study.jwt.springboot.support.jwt;

import java.util.Date;
import java.util.HashMap;

public class Claims extends HashMap<String, Object> {

    public static final String ID = "jti";

    public static final String SUBJECT = "sub";

    public static final String ISSUER = "iss";

    public static final String ISSUED_AT = "iat";

    public static final String EXPIRATION = "exp";

    public static final String NOT_BEFORE = "nbf";

    public static final String AUDIENCE = "aud";

    //编号
    public Claims setId(String jti) {
        put(ID, jti);
        return this;
    }

    public String getId() {
        return (String) get(ID);
    }

    //主题
    public Claims setSubject(String sub) {
        put(SUBJECT, sub);
        return this;
    }

    public String getSubject() {
        return (String) get(SUBJECT);
    }

    //签发人
    public String getIssuer() {
        return (String) get(ISSUER);
    }

    public Claims setIssuer(String iss) {
        put(ISSUER, iss);
        return this;
    }

    //签发时间
    public Claims setIssuedAt(Date iat) {
        put(ISSUED_AT, iat);
        return this;
    }

    public Date getIssuedAt() {
        return (Date) get(ISSUED_AT);
    }

    //过期时间
    public Claims setExpiration(Date exp) {
        put(EXPIRATION, exp);
        return this;
    }

    public Date getExpiration() {
        return (Date) get(EXPIRATION);
    }

    //生效时间
    public Date getNotBefore() {
        return (Date) get(NOT_BEFORE);
    }

    public Claims setNotBefore(Date nbf) {
        put(NOT_BEFORE, nbf);
        return this;
    }
}
