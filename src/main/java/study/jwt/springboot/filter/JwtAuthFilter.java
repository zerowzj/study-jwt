package study.jwt.springboot.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import study.jwt.springboot.support.jwt.JwtUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            //Step-1:
            String jwt = request.getHeader(X_JWT);
            boolean isLegal = JwtUtils.verifyJwt(jwt);
            if (!isLegal) {
                throw new RuntimeException("签名错误");
            }

            JwtToken jwtToken = new JwtToken(jwt);
        } catch (Exception ex) {
            throw ex;
        } finally {

        }
    }
}
