package jejeongmin.MakeAnything.item.domain.dto;

import jejeongmin.MakeAnything.common.enums.ItemType;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemDto {

    @NotNull
    private ItemType type;

    @NotBlank
    private String user;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Integer price;

    @NotBlank
    private String thumbnail;

}
