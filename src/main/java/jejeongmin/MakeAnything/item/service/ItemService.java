package jejeongmin.MakeAnything.item.service;

import jejeongmin.MakeAnything.item.domain.dto.ItemDto;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import jejeongmin.MakeAnything.user.domain.entity.User;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ItemService {

    List<Item> getRecentItems();

    Item createItem(User user, ItemDto itemDto);

    String uploadImage(MultipartFile file) throws IOException;

}
