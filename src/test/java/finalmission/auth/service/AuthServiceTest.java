package finalmission.auth.service;

import finalmission.auth.dto.LoginDto;
import finalmission.user.User;
import finalmission.user.fixture.UserFixture;
import finalmission.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class AuthServiceTest {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void afterEach() {
        user = UserFixture.createDefault();
    }

    @Nested
    @DisplayName("로그인 기능")
    class login {

        @DisplayName("로그인 성공")
        @Test
        void login_success_byInvalidLoginDto() {
            // given
            User savedUser = userRepository.save(user);

            // when
            String token = authService.login(new LoginDto(savedUser.getEmail(), savedUser.getPassword()));

            // then
            System.out.println(token);
        }
    }
}
