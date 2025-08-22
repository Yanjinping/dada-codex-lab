package com.dada.codex.user;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPageResponse listUsers(int page, int size) {
        Page<User> result = userRepository.findAll(PageRequest.of(page - 1, size));
        List<UserDto> list = result.getContent().stream().map(UserDto::fromEntity).toList();
        return new UserPageResponse(result.getTotalElements(), page, list);
    }
}
