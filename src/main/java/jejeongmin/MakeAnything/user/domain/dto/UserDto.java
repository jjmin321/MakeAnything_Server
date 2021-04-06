package jejeongmin.MakeAnything.user.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserDto {
    @NotBlank
    private String id;

    @NotBlank
    private String pw;
}
