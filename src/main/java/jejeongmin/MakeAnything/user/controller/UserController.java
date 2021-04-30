package jejeongmin.MakeAnything.user.controller;

import jejeongmin.MakeAnything.common.annotation.AuthorizationCheck;
import jejeongmin.MakeAnything.common.annotation.AutoLogging;
import jejeongmin.MakeAnything.common.annotation.AutoLoggingWithUser;
import jejeongmin.MakeAnything.common.lib.Jwt;
import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import jejeongmin.MakeAnything.user.domain.entity.User;
import jejeongmin.MakeAnything.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Jwt jwt;

    @AutoLogging
    @PostMapping("/signIn")
    public Response signIn(@Valid @RequestBody UserDto userDto) throws IOException {
        Map<String, String> jsonWebToken = userService.signIn(userDto);
        return new ResponseData<Map<String, String>>(HttpStatus.OK, "로그인 성공", jsonWebToken);
    }

    @AutoLoggingWithUser
    @AuthorizationCheck
    @GetMapping("/getInfo")
    public Response getInfo(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return new ResponseData<User>(HttpStatus.OK, "내 정보 조회 성공", user);
    }

    @GetMapping("/token")
    public Response token(@RequestParam String refreshToken) {
        String accessToken = jwt.refresh(refreshToken);
        return new ResponseData<String>(HttpStatus.OK, "토큰 갱신 성공", accessToken);
    }

}
