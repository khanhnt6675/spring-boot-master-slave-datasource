package vn.com.khanhnt.masterslavedatasource.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.khanhnt.masterslavedatasource.dto.UserDTO;
import vn.com.khanhnt.masterslavedatasource.entity.User;
import vn.com.khanhnt.masterslavedatasource.repository.UserRepository;
import vn.com.khanhnt.masterslavedatasource.service.UserService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDTO getUserInfo(String username) {

    User user = findUserByUsername(username);

    return UserDTO.builder()
        .username(user.getUsername())
        .fullName(user.getFullName())
        .build();

  }

  @Override
  @Transactional(readOnly = true)
  public User findUserByUsername(String username) {

    return userRepository.findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format("Not found user with username: %s", username)));

  }

  @Override
  public User createOrUpdateUser(UserDTO userDTO) {

    User user = userRepository.findByUsername(userDTO.getUsername())
        .stream()
        .peek(u -> u.setFullName(userDTO.getFullName()))
        .findFirst()
        .orElseGet(() -> User.builder()
            .username(userDTO.getUsername())
            .fullName(userDTO.getFullName())
            .build());

    log.info("Saved user with username: {}", user.getUsername());

    return userRepository.save(user);

  }

}