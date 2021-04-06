package jejeongmin.MakeAnything.user.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDto {
    @NotBlank
    private String id;

    @NotBlank
    private String pw;
}
