package study.jwt.springboot.support.exception;

import lombok.Getter;

@Getter
public class VException extends RuntimeException {

    private String[] name;

    private ErrCode errCode;

    public VException(ErrCode errCode) {
        this(new String[0], errCode);
    }

    public VException(String name, ErrCode errCode) {
        this(new String[]{name}, errCode);
    }

    public VException(String[] name, ErrCode errCode) {
        super(String.format(errCode.getMessage(), name));
        this.name = name;
        this.errCode = errCode;
    }
}
