package study.jwt.springboot.support.result;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class Results {

    public static Result ok() {
        return ok(null);
    }

    public static Result ok(Map<String, Object> data) {
        return build("0000", "成功", data);
    }

    public static Result build(String code, String desc, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setDesc(desc);
        if (data == null) {
            data = Maps.newHashMap();
        }
        result.setData(data);
        return result;
    }
}
