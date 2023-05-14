package com.zzqedu.dousheng.core.util;


import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * jwt工具
 */
@Slf4j
public class JWTUtil {
    private static final String SECRET = "1234567890p[]l;'";

    private static final Long EXPIRE_TIME  = 24 * 60 * 60 * 60 * 1000L;

    public static String createToken(Long userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                // 设置有效载荷
                .setClaims(claims)
                // 设置签发时间
                .setIssuedAt(new Date())
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                // 采用HS256方式签名，key就是用来签名的秘钥
                .signWith(SignatureAlgorithm.HS256, SECRET);
        return jwtBuilder.compact();
    }

    public static Long checkToken(String token) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(SECRET).parse(token);
            Map<String,Object> content =  (Map<String, Object>) parse.getBody();
            return Long.parseLong(content.get("userId").toString());
        } catch (Exception e) {
            log.info("解析token出错", e);
            return null;
        }
    }
}
