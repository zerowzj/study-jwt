package study.jwt.springboot.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.jwt.springboot.support.jwt.JwtUtils;
import study.jwt.springboot.support.jwt.Payload;
import study.jwt.springboot.support.utils.TokenGenerator;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class LoginService {

    public String login() {
        Payload payload = new Payload();
        payload
                //.setId("12312")
                //.setSubject("subject")
                //.setIssuer("wzngzhj")
//                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 5000));

        String token = TokenGenerator.createToken();
        Map<String, String> claims = Maps.newHashMap();
        claims.put("email", "wangzhenjun3@xdf.cn");
        claims.put("token", token);
        payload.setClaims(claims);
        String jwt = JwtUtils.createJwt(payload);
        return jwt;
    }
}
