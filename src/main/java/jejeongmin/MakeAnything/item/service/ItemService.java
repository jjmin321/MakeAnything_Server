package jejeongmin.MakeAnything.item.service;

import jejeongmin.MakeAnything.item.domain.dto.ItemDto;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import jejeongmin.MakeAnything.user.domain.entity.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Transactional(readOnly = true)
public interface ItemService {

    Item getItem(String name);

    List<Item> getAllItems();

    List<Item> getAllItemsDesc();

    List<Item> getAllItemsByPrice();

    List<Item> getAllItemsByPriceDesc();

    List<Item> getTalentItems();

    List<Item> getUsedItems();

    List<Item> getCustomItems();

    List<Item> searchItems(String name);

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    Item createItem(User user, ItemDto itemDto);

    String uploadImage(MultipartFile file) throws IOException;

}
