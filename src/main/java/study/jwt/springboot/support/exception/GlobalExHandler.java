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
        if (ex instanceof VException) {
            VException vex = (VException) ex;
            //参数名
            String[] name = vex.getName();
            //错误码
            ErrCode errCode = vex.getErrCode();
            //构造 Result
            String code = errCode.getCode();
            String desc = String.format(errCode.getMessage(), name);
            return Results.fail(code, desc);
        } else {
            return Results.fail();
        }
    }
}
