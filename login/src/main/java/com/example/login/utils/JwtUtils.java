package com.example.login.utils;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;

/**
 * Notice:
 *
 * @author xuxu
 * @version 1.0
 * @date 2020/9/2
 * @since 1.0
 */
@Slf4j
public class JwtUtils {
    private final static String SECRET_KEY = "test";
    private final static Duration EXPIRATION = Duration.ofHours(2);

    public static String generate(String userName){
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRATION.toMillis());
        return Jwts.builder().setSubject(userName).setIssuedAt(new Date())
                .setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }
    public static Claims parseToken(String token){
        if (StrUtil.isEmpty(token)){
            return null;
        }
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        }catch (Exception e){
            log.info("解析失败");
        }
        return claims;
    }
}
