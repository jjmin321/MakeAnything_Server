package jejeongmin.MakeAnything.item.service;

import jejeongmin.MakeAnything.common.enums.ItemType;
import jejeongmin.MakeAnything.item.domain.dto.ItemDto;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import jejeongmin.MakeAnything.user.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ItemService {

    Item getItem(String name);

    Map<ItemType, List<Item>> getRecentItems();

    List<Item> searchItems(String name);

    Item createItem(User user, ItemDto itemDto);

    String uploadImage(MultipartFile file) throws IOException;

}
