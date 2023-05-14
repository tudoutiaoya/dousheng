package com.zzqedu.dousheng.core.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.zzqedu.dousheng.core.common.constant.ErrorCodeEnum;
import com.zzqedu.dousheng.core.common.constant.RedisConstant;
import com.zzqedu.dousheng.core.common.exception.BusinessException;
import com.zzqedu.dousheng.core.util.JWTUtil;
import com.zzqedu.dousheng.core.util.UserHolder;
import com.zzqedu.dousheng.dto.resp.UserRespDto;
import com.zzqedu.dousheng.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;

@RequiredArgsConstructor
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行静态资源
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getParameter("token");

        log.info("===============request start===============");
        log.info("request uri:{}", request.getRequestURI());
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("===============request end===============");

        if (StrUtil.isBlankIfStr(token)) {
            throw new BusinessException(ErrorCodeEnum.USER_LOGIN_EXPIRED);
        }
        Long userId = JWTUtil.checkToken(token);
        if (userId == null) {
            throw new BusinessException(ErrorCodeEnum.TOKEN_IS_INVALID);
        }
        // redis中查询用户信息
        String userJson = stringRedisTemplate.opsForValue().get(RedisConstant.TOKEN + token);
        UserRespDto userRespDto = JSONObject.parseObject(userJson, UserRespDto.class);
        if (userRespDto == null || !userRespDto.getId().equals(userId)) {
            throw new BusinessException(ErrorCodeEnum.TOKEN_IS_INVALID);
        }
        // 将用户信息存入到threadLocal中
        UserHolder.put(userRespDto);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 防止内存泄漏
        UserHolder.remove();
    }
}
