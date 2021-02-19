package study.jwt.springboot.support.exception;

import lombok.Getter;

@Getter
public class VException extends RuntimeException {

    private String name;

    private ErrCode errCode;

    public VException(String name, ErrCode errCode){
        this.name = name;
        this.errCode = errCode;
    }
}
