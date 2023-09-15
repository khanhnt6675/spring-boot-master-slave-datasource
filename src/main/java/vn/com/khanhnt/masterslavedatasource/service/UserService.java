package vn.com.khanhnt.masterslavedatasource.service;

import vn.com.khanhnt.masterslavedatasource.dto.UserDTO;
import vn.com.khanhnt.masterslavedatasource.entity.User;

public interface UserService {

  UserDTO getUserInfo(String username);

  User findUserByUsername(String username);

  User createOrUpdateUser(UserDTO userDTO);

}