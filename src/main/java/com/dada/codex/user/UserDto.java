package com.dada.codex.user;

import java.time.LocalDateTime;

public record UserDto(Long id, String phone, String nickname, LocalDateTime createTime) {
    public static UserDto fromEntity(User user) {
        return new UserDto(user.getId(), user.getPhone(), user.getNickname(), user.getCreateTime());
    }
}
