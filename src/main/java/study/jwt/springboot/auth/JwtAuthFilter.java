package study.jwt.springboot.auth;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import study.jwt.springboot.support.exception.ErrCode;
import study.jwt.springboot.support.jwt.JwtUtils;
import study.jwt.springboot.support.result.Results;
import study.jwt.springboot.support.utils.JsonUtils;
import study.jwt.springboot.support.utils.WebUtils;

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
        if (CollectionUtils.contains(authIgnoreLt.iterator(), uri)) {
            log.info(">>>>>> [{}] ignore auth!", uri);
            doFilter(request, response, filterChain);
            return;
        }
        try {
            //Step-1: 验证jwt合法性
            String jwt = request.getHeader(X_TOKEN);
            if (Strings.isNullOrEmpty(jwt)) {
                WebUtils.write(response, Results.fail(ErrCode.AUTH_TOKEN_EMPTY_ERROR));
                return;
            }
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
