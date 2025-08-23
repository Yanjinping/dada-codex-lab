package com.dada.codex.user;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPageResponse listUsers(int page, int size, String kw, String sortBy, String order) {
        int currentPage = page > 0 ? page : 1;
        int pageSize = size > 0 ? size : 10;
        String sortProperty = "id".equalsIgnoreCase(sortBy) ? "id" : "createTime";
        Sort.Direction direction = "asc".equalsIgnoreCase(order) ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest request = PageRequest.of(currentPage - 1, pageSize, Sort.by(direction, sortProperty));
        Page<User> result;
        if (kw != null && !kw.isBlank()) {
            String keyword = kw.trim();
            result = userRepository
                .findByPhoneContainingIgnoreCaseOrNicknameContainingIgnoreCase(keyword, keyword, request);
        } else {
            result = userRepository.findAll(request);
        }
        List<UserDto> list = result.getContent().stream().map(UserDto::fromEntity).toList();
        return new UserPageResponse(result.getTotalElements(), currentPage, pageSize, list);
    }
}
