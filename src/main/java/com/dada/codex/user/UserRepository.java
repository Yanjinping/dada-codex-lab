package com.dada.codex.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByPhoneContainingIgnoreCaseOrNicknameContainingIgnoreCase(String phone,
                                                                            String nickname,
                                                                            Pageable pageable);
}
