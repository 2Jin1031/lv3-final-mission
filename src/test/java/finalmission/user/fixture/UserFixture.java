package finalmission.user.fixture;

import finalmission.user.User;

public class UserFixture {

    public static User create(String name, String email, String password) {
        return new User(name, email, password);
    }

    public static User createDefault() {
        return create("user1", "user1@email.com", "user1password");
    }
}
