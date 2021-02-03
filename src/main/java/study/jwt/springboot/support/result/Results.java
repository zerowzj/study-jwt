package study.jwt.springboot.support.result;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Results {

    private static final Map<String, Object> EMPTY_DATA = Maps.newHashMap();

    public static Result ok() {
        return ok(EMPTY_DATA);
    }

    public static Result ok(Map<String, Object> data) {
        return build("0000", "成功", data);
    }

    public static Result fail() {
        return fail("9999", "系统异常");
    }

    public static Result fail(String code, String desc) {
        return build(code, desc, EMPTY_DATA);
    }

    public static Result build(String code, String desc, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setDesc(desc);
        result.setData(data);
        return result;
    }
}
