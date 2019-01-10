package com.config.jwt;

import com.common.utils.jwt.JwtUtils;
import com.common.utils.redis.RedisUtil;
import com.pc.model.consts.LoginConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * JWT authentication filter
 *
 * @author li.hao
 * @date 2018/11/15 10:22
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private RedisUtil redisUtil;
    private static final Logger jwtlogger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final PathMatcher pathMatcher = new AntPathMatcher();
    private static final String OPTIONS = "OPTIONS";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String reuqestType = request.getMethod();
            if (!isLoginUrl(request) && !OPTIONS.equals(reuqestType)) {
                String token = request.getHeader(LoginConsts.TOKEN);
                if (null == token || !redisUtil.hasKey(token)) {
                    response.sendRedirect("/login");
                } else {
                    Map<String, Object> map = JwtUtils.validateToken(token);
                    jwtlogger.info("account: " + map.get("account") + " access successed !");
                }
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isLoginUrl(HttpServletRequest request) {
        return pathMatcher.match("/login", request.getServletPath());
    }
}
