package study.jwt.springboot.support.exception;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import study.jwt.springboot.support.result.Result;
import study.jwt.springboot.support.result.Results;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 1.注解@ControllerAdvice方式只能处理控制器抛出的异常。此时请求已经进入控制器中。
 * 2.类ErrorController方式可以处理所有的异常，包括未进入控制器的错误，比如404,401等错误
 * 3.如果应用中两者共同存在，则@ControllerAdvice方式处理控制器抛出的异常，类ErrorController方式未进入控制器的异常。
 * 4.@ControllerAdvice方式可以定义多个拦截方法，拦截不同的异常类，并且可以获取抛出的异常信息，自由度更大
 */
@Slf4j
@Controller
class GlobalErrController implements ErrorController {

    private static final String KEY_STATUS_CODE = "javax.servlet.error.status_code";

    private static final String KEY_EXCEPTION = "javax.servlet.error.exception";

    private static final String KEY_MESSAGE = "javax.servlet.error.message";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result error(HttpServletRequest request) {
        int statusCode = (int) request.getAttribute(KEY_STATUS_CODE);
        Exception ex = (Exception) request.getAttribute(KEY_EXCEPTION);
        log.info("statueCode= {}, msg={}", statusCode, ex != null ? ex.getMessage() : "");
        log.error("", ex);
        try {
            ServletWebRequest requestAttributes = new ServletWebRequest(request);
            Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
            log.info("报错信息:" + JSON.toJSONString(attr));
        } catch (Exception e) {
            log.error("获取异常信息错误", e);
        }

        return Results.fail();
    }

}