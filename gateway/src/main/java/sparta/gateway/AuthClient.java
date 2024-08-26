package sparta.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user-service")
public interface AuthClient extends AuthService{
    @GetMapping("/api/user/verify") // 유저 검증 API
    Boolean verifyUser(@RequestParam(value = "user_id") String user_id);
}
