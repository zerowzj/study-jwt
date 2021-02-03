package study.jwt.springboot.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.jwt.springboot.support.jwt.JwtUtils;
import study.jwt.springboot.support.jwt.Payload;
import study.jwt.springboot.support.jwt.TokenGenerator;

import java.util.Map;

@Slf4j
@Service
public class LoginService {

    public String login() {
        String token = TokenGenerator.createToken();

        Map<String, String> claims = Maps.newHashMap();
        claims.put("userId", "900001");
        claims.put("email", "wangzhenjun3@xdf.cn");
        claims.put("token", token);

        Payload payload = new Payload();
        payload.setId("12312");
        payload.setSubject("aaaaaaa");
        payload.setClaims(claims);
        String jwt = JwtUtils.createJwt(payload);
        return jwt;
    }
}
