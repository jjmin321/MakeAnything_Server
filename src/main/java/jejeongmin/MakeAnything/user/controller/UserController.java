package jejeongmin.MakeAnything.user.controller;

import jejeongmin.MakeAnything.common.enums.JwtEnum;
import jejeongmin.MakeAnything.common.lib.Jwt;
import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.user.domain.entity.User;
import jejeongmin.MakeAnything.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param userDto - DodamDodam ID, PW
     * @return response - Status Code, Message, JWT(AccessToken and RefreshToken)
     * @throws Exception - An Exception occurred in UserService Layer
     */

    @PostMapping("/signIn")
    public Response signIn(@Valid @RequestBody UserDto userDto) throws Exception {
        try {
            Map<String, String> jsonWebToken = userService.signIn(userDto);
            return new ResponseData<Object>(HttpStatus.OK, "로그인 성공", jsonWebToken);
        } catch (Exception e) {
            throw e;
        }
    }

}
