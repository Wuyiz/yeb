package com.wuyiz.server.config.security.component;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * @author: suhai
 * @date: 2021-03-07 17:23
 * @description: jwt工具类
 *
 * 可供外界调用的方法：
 *      1. generateToken
 *      2. getUserNameFromToken
 *      3. validateToken
 *      4. canRefresh
 *      5. refreshToken
 */
@Component
public class JwtTokenUtil {
    private static Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expiration;

    /**
     * 根据用户信息生成token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证当前传入token的用户名与服务器保存的值是否匹配，并且判断token是否过期
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return StringUtils.equals(username, userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 解析token获取载荷信息
     * @param token
     * @return
     */
    private Claims getClaimFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // 解析过期token时会有异常，但ExpiredJwtException中保存了过期token信息，返回claim供其他方法获取token信息
            claims = e.getClaims();
        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            // token：签名不对或内容残缺
            logger.error("[JWT解析异常]: {}", e.getMessage(), e);
        }
        return claims;
    }

    /**
     * 判断token是否失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expiration = getExpiredDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 获取token中的过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        return getClaimFormToken(token).getExpiration();
    }

    /**
     * 生成token失效时间
     * @return
     */
    private Date generateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MILLISECOND, expiration);
        return calendar.getTime();
    }
}
