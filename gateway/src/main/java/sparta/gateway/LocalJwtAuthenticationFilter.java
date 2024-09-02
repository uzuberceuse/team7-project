package sparta.gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;

@Component
public class LocalJwtAuthenticationFilter implements GlobalFilter {
    private final String secretKey;
    private final AuthService authService;

    public LocalJwtAuthenticationFilter(@Value("${service.jwt.secret-key}") String secretKey, @Lazy AuthService authService) {
        this.secretKey = secretKey;
        this.authService = authService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path=exchange.getRequest().getURI().getPath();
        // /api/user로 시작하는 요청 검증 X
        if (path.startsWith("/api/user")){
            return chain.filter(exchange);
        }

        String token=extractToken(exchange);
        // 토큰이 존재하지 않거나, validateToken(token) 기준에 부합하지 않으면 401 에러를 응답합니다.
        if (token == null || !validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader=exchange.getRequest().getHeaders().getFirst("Authorization");
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try{
            // String -> SecretKey 변환
            SecretKey key= Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            // JWT에 설정된 정보 불러옴
            Jws<Claims> claimsJws= Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token);

            // JWT 값 중 Payload 부분에 user_id로 설정된 값이 있는 경우
            if (claimsJws.getPayload().get("user_id") != null) {
                // user_id 추출 로직
                String user_id = claimsJws.getPayload().get("user_id").toString();
                // email 값으로 해당 유저가 회원가입 한 유저인지 인증 서비스를 통해 확인합니다.
                return authService.verifyUser(user_id);
            } else {
                return false;
            }
        }catch (Exception e) {
            return false;
        }
    }
}
