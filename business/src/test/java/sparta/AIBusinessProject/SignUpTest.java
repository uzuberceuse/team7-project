package sparta.AIBusinessProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sparta.AIBusinessProject.domain.user.repository.UserRepository;

@SpringBootTest
public class SignUpTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void signUpTest(){
        // builder
    }
}
