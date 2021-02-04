package study.jwt.springboot.support.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;

@Slf4j
public final class JsonUtils {

    private JsonUtils() {
    }

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    public static <T> T fromJson(String text, Class<T> clazz) {
        T obj;
        try {
            obj = JSON.parseObject(text, clazz);
        } catch (Exception ex) {
            log.error("", ex);
            throw ex;
        }
        return obj;
    }

    public static <T> T fromJson(InputStream is, Class<T> clazz) {
        T obj;
        try {
            obj = JSON.parseObject(is, clazz);
        } catch (Exception ex) {
            log.error("", ex);
            throw new RuntimeException();
        }
        return obj;
    }
}
