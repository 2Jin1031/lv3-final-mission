package finalmission.user.domain.dto;

import finalmission.user.User;

public record UserResponseDto(String name, String role, String email) {

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getName(), user.getRole(), user.getEmail());
    }
}
