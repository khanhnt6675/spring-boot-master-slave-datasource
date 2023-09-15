package vn.com.khanhnt.masterslavedatasource.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.khanhnt.masterslavedatasource.dto.UserDTO;
import vn.com.khanhnt.masterslavedatasource.service.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/info")
  public ResponseEntity<UserDTO> getUserInfo(@RequestParam("username") String username) {
    return ResponseEntity.ok(userService.getUserInfo(username));
  }

  @PutMapping("/create-or-update")
  public ResponseEntity<UserDTO> getUserInfo(@RequestBody UserDTO userDTO) {
    userService.createOrUpdateUser(userDTO);
    return ResponseEntity.ok(userDTO);
  }

}