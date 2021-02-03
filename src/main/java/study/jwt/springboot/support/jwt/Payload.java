package study.jwt.springboot.support.jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Payload {
    //签发人
    private String issuer;
    //主题
    private String subject;
    //受众
    private String aud;

//    exp(expiration time)：

//    过期时间
//    sub(subject)：
//
//    主题
//    aud(audience)：
//
//    受众
//    nbf(Not Before)：
//
//    生效时间
//    iat(Issued At)：
//
//    签发时间
//    jti(JWT ID)：编号

    Map<String, Object> claims;
}
