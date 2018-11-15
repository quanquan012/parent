package com.common.utils.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT utils
 *
 * @author li.hao
 * @date 2018/11/15 10:30
 */
public class JwtUtils {

    static final String SECRET = "66.0x7c#8583";

    public static String generateToken(String userAccount) {
        HashMap<String, Object> map = new HashMap<>();
        //you can put any data in the map
        map.put("account", userAccount);
        String jwt = Jwts.builder()
                .setClaims(map)
                // 1000 hour
                .setExpiration(new Date(System.currentTimeMillis() + 3600_000_000L))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        //jwt前面一般都会加Bearer
        return "Bearer " + jwt;
    }

    public static Map<String, Object> validateToken(String token) {
        try {
            // parse the token.
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
            return body;
        } catch (Exception e) {
            throw new IllegalStateException("Invalid Token. " + e.getMessage());
        }
    }

}
