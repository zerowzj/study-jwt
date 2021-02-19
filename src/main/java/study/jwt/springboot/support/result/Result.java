package study.jwt.springboot.support.result;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result<T> {

    private String code;

    private String desc;

    private T data;

    private String requestId;

    protected Result() {
    }
}
