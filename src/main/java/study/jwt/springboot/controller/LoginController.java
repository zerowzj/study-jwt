package study.jwt.springboot.controller;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.jwt.springboot.service.LoginService;
import study.jwt.springboot.support.result.Result;
import study.jwt.springboot.support.result.Results;

@Slf4j
@RestController
public class LoginController {

    @Setter
    private LoginService loginService;

    @RequestMapping("/login")
    public Result login() {
        String jwt = loginService.login();
        log.info(">>>>>> {}", jwt);
        return Results.ok();
    }
}
