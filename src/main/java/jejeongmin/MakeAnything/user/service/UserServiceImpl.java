package jejeongmin.MakeAnything.user.service;

import jejeongmin.MakeAnything.common.enums.JwtEnum;
import jejeongmin.MakeAnything.common.lib.Encrypt;
import jejeongmin.MakeAnything.common.lib.Jwt;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.user.domain.entity.User;
import jejeongmin.MakeAnything.user.domain.repository.UserRepository;
import jejeongmin.MakeAnything.common.retrofit.service.DodamService;
import jejeongmin.MakeAnything.common.retrofit.vo.DodamUserDataVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Encrypt encrypt;

    @Autowired
    private Jwt jwt;

    @Autowired
    private DodamService dodamService;

    private ModelMapper modelMapper = new ModelMapper();

    /**
     * @param userDto - ID and PW
     * @return User - an User Record
     * @throws Exception - An Exception occurred in DodamService or UserRepository Layer
     */

    @Override
    public Map<String, String> signIn(UserDto userDto) throws Exception {
        try {
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
        } catch (Exception e) {
            throw e;
        }
    }

}
