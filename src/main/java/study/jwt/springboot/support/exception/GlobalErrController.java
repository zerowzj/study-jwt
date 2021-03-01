package study.jwt.springboot.support.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import study.jwt.springboot.support.result.Results;
import study.jwt.springboot.support.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Controller
class GlobalErrController implements ErrorController {

    private static final String PATH = "/error";

    private static final String KEY_STATUS_CODE = "javax.servlet.error.status_code";

    private static final String KEY_EXCEPTION = "javax.servlet.error.exception";

    private static final String KEY_MESSAGE = "javax.servlet.error.message";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object error(HttpServletRequest request, HttpServletResponse response) {
        //解析 Exception
        Exception ex = (Exception) request.getAttribute(KEY_EXCEPTION);
        if (ex != null) {
            if (ex instanceof VException) {
                //VException
                VException vex = (VException) ex;
                return Results.fail(vex);
            } else {
                //other Exception
                return Results.fail();
            }
        } else {
            ServletWebRequest requestAttributes = new ServletWebRequest(request);
            Map<String, Object> errAttr = errorAttributes.getErrorAttributes(requestAttributes, false);
            log.info("报错信息: {}", JsonUtils.toJson(errAttr));
            return errAttr;
        }
    }
}