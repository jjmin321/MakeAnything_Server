package jejeongmin.MakeAnything.user.service;

import jejeongmin.MakeAnything.user.domain.dto.UserDto;

import java.util.Map;

public interface UserService {

    Map<String, String> signIn(UserDto userDto) throws Exception;

}
