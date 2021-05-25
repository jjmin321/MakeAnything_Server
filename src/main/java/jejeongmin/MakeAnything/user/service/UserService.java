package jejeongmin.MakeAnything.user.service;

import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;

@Transactional(isolation = Isolation.READ_UNCOMMITTED)
public interface UserService {

    Map<String, String> signIn(UserDto userDto) throws IOException;

}
