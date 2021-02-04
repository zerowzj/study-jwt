package study.jwt.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.jwt.springboot.support.jwt.Claims;
import study.jwt.springboot.support.jwt.JwtUtils;
import study.jwt.springboot.support.jwt.TokenGenerator;

import java.util.Date;

@Slf4j
@Service
public class LoginService {

    public String login() {
        String token = TokenGenerator.createToken();
//        Map<String, String> claims = Maps.newHashMap();
//        claims.put("email", "wangzhenjun3@xdf.cn");
//        claims.put("token", token);
        Claims claims = new Claims();
        claims.setId("12312");
        claims.setExpiration(new Date(System.currentTimeMillis() + 50000));
        String jwt = JwtUtils.createJwt(claims);
        return jwt;
    }
}
