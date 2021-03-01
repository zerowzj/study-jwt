package study.jwt.springboot.support.result;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import study.jwt.springboot.support.exception.VException;

import java.util.Map;

@Slf4j
public class Results {

    private static final Map<String, Object> EMPTY_DATA = Maps.newHashMapWithExpectedSize(0);

    /**
     * 成功
     *
     * @return Result
     */
    public static Result ok() {
        return ok(EMPTY_DATA);
    }

    /**
     * 成功
     *
     * @param data
     * @return Result
     */
    public static Result ok(Map<String, Object> data) {
        return build(ErrCode.SUCCESS, data);
    }

    /**
     * 失败
     *
     * @return Result
     */
    public static Result fail() {
        return build(ErrCode.SYS_EXCEPTION);
    }

    /**
     * 失败
     *
     * @param vex
     * @return Result
     */
    public static Result fail(VException vex) {
        //参数名
        String[] name = vex.getName();
        //错误码
        ErrCode errCode = vex.getErrCode();
        //构造 Result
        String code = errCode.getCode();
        String desc = String.format(errCode.getMsg(), name);
        return fail(code, desc);
    }

    /**
     * 失败
     *
     * @param code
     * @param desc
     * @return Result
     */
    public static Result fail(String code, String desc) {
        return build(code, desc, EMPTY_DATA);
    }

    //===============================================
    private static Result build(ErrCode errCode) {
        String code = errCode.getCode();
        String desc = errCode.getMsg();
        return build(code, desc, EMPTY_DATA);
    }

    private static Result build(ErrCode errCode, Object data) {
        String code = errCode.getCode();
        String desc = errCode.getMsg();
        return build(code, desc, data);
    }

    private static Result build(String code, String desc, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setDesc(desc);
        if (data == null) {
            data = EMPTY_DATA;
        }
        result.setData(data);
        return result;
    }
}
