package jejeongmin.MakeAnything.item.domain.dto;

import jejeongmin.MakeAnything.common.enums.ItemType;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import jejeongmin.MakeAnything.user.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ItemDto {

    @NotNull
    private ItemType type;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Integer price;

    @NotBlank
    private String thumbnail;

    public Item toEntity(User user) {
        return Item.builder()
                .type(type)
                .user(user)
                .name(name)
                .description(description)
                .price(price)
                .thumbnail(thumbnail)
                .build();
    }

}
