package study.jwt.springboot.controller;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.jwt.springboot.service.LoginService;
import study.jwt.springboot.support.result.Result;
import study.jwt.springboot.support.result.Results;

import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public Result login() {
        String jwt = loginService.login();
        Map<String, Object> data = Maps.newHashMap();
        data.put("jwt", jwt);
        return Results.ok(data);
    }

    @RequestMapping("/logout")
    public Result logout(@RequestHeader("X-Token") String token) {
        return Results.ok();
    }

    @RequestMapping("/throwEx")
    public Result throwEx() {
        String str = null;

        return Results.ok();
    }
}
