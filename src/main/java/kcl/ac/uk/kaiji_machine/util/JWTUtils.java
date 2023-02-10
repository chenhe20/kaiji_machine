package kcl.ac.uk.kaiji_machine.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */
public class JWTUtils {
    // expire time: 1 day
    public static final long EXPIRE = 1000 * 60 * 60 * 24;

    // encryption key
    public static final String APP_SECRET = "abcdefg";

    public static String getJwtToken(String username) {
        String jwtToken = Jwts.builder()
                // Sets token headers
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                // Sets token subject
                .setSubject("token-demo")
                // Sets token created timestamp
                .setIssuedAt(new Date())
                // Sets token expire time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // Sets token valid payload
                .claim("username", username)
                // Sets signature，secret key and HS256 encryption
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();

        return jwtToken;
    }

    public static boolean checkToken(String jwtToken) {
        if (null == jwtToken || jwtToken.isEmpty()) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getUsernameByJwtToken(String jwtToken) {
        if (jwtToken.isEmpty()) {
            return "";
        }
        Jws<Claims> claimsJws =
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("username");
    }

}
