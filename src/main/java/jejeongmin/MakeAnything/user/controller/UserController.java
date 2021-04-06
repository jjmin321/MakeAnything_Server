package jejeongmin.MakeAnything.user.controller;

import jejeongmin.MakeAnything.common.vo.http.Response;
import jejeongmin.MakeAnything.common.vo.http.ResponseData;
import jejeongmin.MakeAnything.user.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Value("${dodam.api-key}") private String dodamApiKey;

    @GetMapping("/signIn")
    public Response signIn(@Valid @RequestBody UserDto userDto) {
        try {
            System.out.println(dodamApiKey);
            return new ResponseData(HttpStatus.OK, "로그인 성공", userDto);
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}
