package jejeongmin.MakeAnything.item.service;

import jejeongmin.MakeAnything.common.enums.ItemType;
import jejeongmin.MakeAnything.common.exception.DuplicateRecordException;
import jejeongmin.MakeAnything.common.exception.FileIsEmptyException;
import jejeongmin.MakeAnything.common.lib.File;
import jejeongmin.MakeAnything.item.domain.dto.ItemDto;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import jejeongmin.MakeAnything.item.domain.repository.ItemRepository;
import jejeongmin.MakeAnything.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final File fileLib;
    private final ItemRepository itemRepository;

    @Override
    public Item getItem(String name) { return itemRepository.findByName(name); }

    @Override
    public List<Item> getAllItems() { return itemRepository.findAllByOrderByCreatedAt(); }

    @Override
    public List<Item> getAllItemsDesc() { return itemRepository.findAllByOrderByCreatedAtDesc(); }

    @Override
    public List<Item> getAllItemsByPrice() { return itemRepository.findAllByOrderByPrice(); }

    @Override
    public List<Item> getAllItemsByPriceDesc() { return itemRepository.findAllByOrderByPriceDesc(); }

    @Override
    public List<Item> getTalentItems() { return itemRepository.findAllByType(ItemType.TALENT); }

    @Override
    public List<Item> getUsedItems() { return itemRepository.findAllByType(ItemType.USED); }

    @Override
    public List<Item> getCustomItems() { return itemRepository.findAllByType(ItemType.CUSTOM); }

    @Override
    public List<Item> searchItems(String name) { return itemRepository.findAllByNameContaining(name); }

    @Override
    public Item createItem(User user, ItemDto itemDto) {
        if (itemRepository.findByName(itemDto.getName()) != null) {
            throw new DuplicateRecordException("같은 물품명이 이미 존재합니다. 다른 물품명으로 등록해주세요.");
        }
        return itemRepository.save(itemDto.toEntity(user));
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new FileIsEmptyException();
        }
        return fileLib.uploadFile(file);
    }

}
