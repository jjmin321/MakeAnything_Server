package jejeongmin.MakeAnything.user.service;

import jejeongmin.MakeAnything.common.exception.EncryptException;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.user.domain.entity.User;

public interface UserService {
    public User signIn(UserDto userDto) throws Exception; //signIn, signUp CMD 사용
}
