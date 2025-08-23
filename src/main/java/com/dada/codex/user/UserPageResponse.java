package com.dada.codex.user;

import java.util.List;

public record UserPageResponse(long total, int page, int size, List<UserDto> data) {
}
