package study.jwt.springboot.service;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import study.jwt.springboot.support.jwt.JwtUtils;

import java.util.Map;

@Service
public class LoginService {

    public String login() {
        Map<String, Object> claims = Maps.newHashMap();
        claims.put("token", "1111111111111");
        String jwt = JwtUtils.createJwt(claims);

        return jwt;
    }
}
