package study.jwt.springboot.support.exception;

public class Asserts {

    public static void NotEmpty(String value, String name) {
        if (value == null) {
            throw new VException(name, ErrCode.PARAM_EMPTY);
        }
    }

}
