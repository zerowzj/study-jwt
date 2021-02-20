package study.jwt.springboot.support.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.jwt.springboot.support.result.Result;
import study.jwt.springboot.support.result.Results;

@Slf4j
@RestControllerAdvice
public class GlobalExHandler {

    @ExceptionHandler(Throwable.class)
    public Result resolveException(Exception ex) {
        log.error("", ex);
        if (ex instanceof VException) { //验证异常
            VException vex = (VException) ex;
            return Results.fail(vex);
        } else { //其他异常
            return Results.fail();
        }
    }
}
