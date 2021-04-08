package jejeongmin.MakeAnything.user.service;

import jejeongmin.MakeAnything.common.lib.Encrypt;
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

@Service
public class UserServiceImpl implements UserService {

    @Value("${dodam.api-key}")
    private String dodamApiKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Encrypt encrypt;

    @Autowired
    private DodamService dodamService;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public User signIn(UserDto userDto) throws Exception {
        try {
            userDto.setPw(encrypt.sha512(userDto.getPw()));
            String dodamToken = dodamService.authLogin(userDto, dodamApiKey);
            DodamUserDataVo dodamUserDataVo = dodamService.getUserInfo(dodamToken);
            User mappedUser = modelMapper.map(dodamUserDataVo, User.class);
            User createdUser = userRepository.save(mappedUser);
            // String jwt = jwt.createToken
            return createdUser;
        } catch (Exception e) {
            throw e;
        }
    }

}
