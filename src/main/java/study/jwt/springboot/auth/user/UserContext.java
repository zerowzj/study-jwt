package study.jwt.springboot.auth.user;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserContext {

    private static final TransmittableThreadLocal<UserSessionInfo> LOCAL = new TransmittableThreadLocal<UserSessionInfo>() {
        @Override
        protected void beforeExecute() {
            log.info("before execute");
        }

        @Override
        protected void afterExecute() {
            log.info("after execute");
        }
    };

    public static void set(UserSessionInfo sessionInfo) {
        LOCAL.set(sessionInfo);
    }

    public static UserSessionInfo get() {
        return LOCAL.get();
    }

    public static void remove() {
        LOCAL.remove();
    }
}
