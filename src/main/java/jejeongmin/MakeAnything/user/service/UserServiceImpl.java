package jejeongmin.MakeAnything.user.service;

import jejeongmin.MakeAnything.common.enums.JwtEnum;
import jejeongmin.MakeAnything.common.lib.Encrypt;
import jejeongmin.MakeAnything.common.lib.Jwt;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.user.domain.entity.User;
import jejeongmin.MakeAnything.user.domain.repository.UserRepository;
import jejeongmin.MakeAnything.common.retrofit.service.DodamService;
import jejeongmin.MakeAnything.common.vo.dodam.DodamUserDataVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Encrypt encrypt;
    private final Jwt jwt;
    private final DodamService dodamService;
    private final ModelMapper modelMapper;

    @Override
    public Map<String, String> signIn(UserDto userDto) throws IOException {
        userDto.setPw(encrypt.sha512(userDto.getPw()));
        String dodamToken = dodamService.authLogin(userDto);
        DodamUserDataVo dodamUserDataVo = dodamService.getUserInfo(dodamToken);
        User mappedUser = modelMapper.map(dodamUserDataVo, User.class);
        User createdUser = userRepository.save(mappedUser);
        String accessToken = jwt.createToken(createdUser, JwtEnum.ACCESS);
        String refreshToken = jwt.createToken(createdUser, JwtEnum.REFRESH);
        Map<String, String> token = new HashMap<String, String>();
        token.put("accessToken", accessToken);
        token.put("refreshToken", refreshToken);
        return token;
    }

}
