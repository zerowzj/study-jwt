package study.jwt.springboot.support.jwt;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Payload {
    //编号
    private String id;
    //主题
    private String subject;
    //签发人
    private String issuer;
    //签发时间
    private Date issuedAt;
    //生效时间
    private Date notBefore;
    //过期时间
    private Date expiresAt;
    //
    List<String> audience;

    //
    private Map<String, String> claims;
}
