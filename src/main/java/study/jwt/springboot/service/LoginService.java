package study.jwt.springboot.service;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.jwt.springboot.support.jwt.JwtUtils;
import study.jwt.springboot.support.jwt.Payload;
import study.jwt.springboot.support.utils.TokenGenerator;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginService {

    public String login() {
        Payload payload = new Payload();
//        payload
                //.setId("12312")
                //.setSubject("subject")
                //.setIssuer("wzngzhj")
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 500*1000));

        Stopwatch stopwatch = Stopwatch.createStarted();
        String token = UUID.randomUUID().toString();
        log.info("token cost time {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        Map<String, String> claims = Maps.newHashMap();
        claims.put("email", "wangzhenjun3@xdf.cn");
        claims.put("token", token);
        payload.setClaims(claims);
        stopwatch.reset();
        String jwt = JwtUtils.createJwt(payload);
        log.info("jwt cost time {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return jwt;
    }
}
