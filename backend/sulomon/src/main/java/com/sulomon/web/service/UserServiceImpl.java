package com.sulomon.web.service;

import com.sulomon.web.dto.UserDTO;
import com.sulomon.auth.entity.UserEntity;
import com.sulomon.web.repository.UserRepository;
import com.sulomon.web.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository ur;
    // 암호화
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean idCheck(String searchId) {
        return !ur.existsByUserId(searchId);
    }

    @Override
    public void join(UserDTO userDTO) {
        // UserDTO -> UserEntity 변환
        UserEntity userEntity = convertDtoToEntity(userDTO);
        log.info("UserEntity 정보: {}", userEntity);

        // 데이터베이스에 저장
        ur.save(userEntity);
        log.info("회원가입 성공 아이디: {}", userDTO.getUserId());
    }

    @Override
    public boolean passwordCheck(String username, String password) {
        UserEntity user = ur.findByUserId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String userPassword = user.getPassword();
        log.info("userPassword : ", userPassword);
        return passwordEncoder.matches(password, userPassword);

    }

    // UserDTO -> UserEntity 변환 메서드
    private UserEntity convertDtoToEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .userNum(userDTO.getUserNum())
                .userId(userDTO.getUserId())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .birthday(userDTO.getBirthday())
                .gender(userDTO.getGender())
                .socialLogin(userDTO.isSocialLogin())
                .role(userDTO.getRole())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .mbti(userDTO.getMbti())
                .points(userDTO.getPoints())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(LocalDateTime.now())  // 계정 수정 시간은 현재 시각으로 설정
                .status(userDTO.getStatus())
                .build();
    }
}
