package study.jwt.springboot.auth;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import study.jwt.springboot.support.jwt.JwtUtils;
import study.jwt.springboot.support.utils.JsonUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String X_JWT = "X-Token";

    @Value("#{'${auth.ignoreList}'.split(',')}")
    private List<String> authIgnoreLt;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {
            if (CollectionUtils.contains(authIgnoreLt.iterator(), uri)) {
                log.warn(">>>>>> ignore auth[{}]", uri);
                doFilter(request, response, filterChain);
                return;
            }
            //Step-1: 验证 jwt 合法性
            String jwt = request.getHeader(X_JWT);
//            boolean isLegal = JwtUtils.verifyJwt(jwt);
//            if (!isLegal) {
//                throw new RuntimeException("签名错误");
//            }
            //Step-2: 获取 jwt
            Claims claims = JwtUtils.parseJwt(jwt);
            log.info("{}", JsonUtils.toJson(claims));
            doFilter(request, response, filterChain);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
