package study.jwt.springboot.support.result;

import lombok.Getter;

@Getter
public enum ErrCode {

    SUCCESS("0000", "成功"),

    //参数：1xxx
    PARAM_EMPTY("1001", "参数值为空[%s]"),
    PARAM_FORMAT_ERROR("1002", "参数值格式错误[%s]"),
    PARAM_VALUE_ILLEGAL("1003", "参数值非法[%s]"),
    //业务：2xxx
    BUSINESS_NOT_FOUND("2001", "[%s]不存在"),
    BUSINESS_ALREADY_EXISTS("2002", "[%s]已存在"),
    BUSINESS_ERROR("2003", "[%s]错误"),
    //认证：3xxx
    AUTH_TOKEN_EMPTY_ERROR("3001", "token为空或错误"),
    AUTH_TOKEN_EXPIRED("3002", "token过期"),
    AUTH_RE_LOGIN("3004", "请先登录"),

//    SIGN_EMPTY(501, "验签参数不全"),
//    TIMESTAMP_EXPIRED(502, "请求已过期"),
//    SIGN_FAIL(503, "验签失败"),

    //系统：9xxx
    SYS_EXCEPTION("9999", "系统异常");

    private String code;

    private String msg;

    ErrCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
