package study.jwt.springboot.support.exception;

import lombok.Getter;
import study.jwt.springboot.support.result.ErrCode;

@Getter
public class VException extends RuntimeException {

    private String[] name;

    private ErrCode errCode;

    public VException(ErrCode errCode) {
        this("", errCode);
    }

    public VException(String name, ErrCode errCode) {
        this(new String[]{name}, errCode);
    }

    public VException(String[] name, ErrCode errCode) {
        super(String.format(errCode.getMsg(), name));
        this.name = name;
        this.errCode = errCode;
    }
}
