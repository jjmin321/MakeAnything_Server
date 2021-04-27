package jejeongmin.MakeAnything.user.service;

import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import java.io.IOException;
import java.util.Map;

public interface UserService {

    Map<String, String> signIn(UserDto userDto) throws IOException;

}
