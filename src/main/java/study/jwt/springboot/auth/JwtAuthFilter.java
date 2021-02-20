package study.jwt.springboot.auth;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import study.jwt.springboot.support.exception.VException;
import study.jwt.springboot.support.jwt.JwtUtils;
import study.jwt.springboot.support.result.ErrCode;
import study.jwt.springboot.support.utils.JsonUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String X_TOKEN = "X-Token";

    @Value("#{'${auth.ignoreList}'.split(',')}")
    private List<String> authIgnoreLt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //ignore auth
        String uri = request.getRequestURI();
        if (CollectionUtils.containsAny(authIgnoreLt, uri)) {
            log.info(">>>>>> [{}] ignore auth!", uri);
            doFilter(request, response, filterChain);
            return;
        }
        try {
            String jwt = request.getHeader(X_TOKEN);
            if (Strings.isNullOrEmpty(jwt)) {
                throw new VException(ErrCode.AUTH_TOKEN_EMPTY_ERROR);
            }

            //Step-1: 验证jwt
            JwtUtils.verifyJwt(jwt);
            //Step-2: 获取jwt
            Map<String, String> claims = JwtUtils.parseJwt(jwt);
            log.info("{}", JsonUtils.toJson(claims));
            String token = claims.get("token");
            log.info(">>>>>> token= {}", token);

            doFilter(request, response, filterChain);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
