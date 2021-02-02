package study.jwt.springboot.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.jwt.springboot.support.jwt.JwtUtils;
import study.jwt.springboot.support.jwt.TokenGenerator;

import java.util.Map;

@Slf4j
@Service
public class LoginService {

    public String login() {
        String token = TokenGenerator.createToken();
        log.info(">>>>>> token= {}", token);
        Map<String, Object> claims = Maps.newHashMap();
        claims.put("token", token);
        String jwt = JwtUtils.createJwt(claims);

        return jwt;
    }
}
