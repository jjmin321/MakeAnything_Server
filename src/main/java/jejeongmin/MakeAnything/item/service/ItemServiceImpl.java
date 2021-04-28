package jejeongmin.MakeAnything.item.service;

import jejeongmin.MakeAnything.common.exception.FileIsEmptyException;
import jejeongmin.MakeAnything.common.lib.File;
import jejeongmin.MakeAnything.item.domain.dto.ItemDto;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import jejeongmin.MakeAnything.item.domain.repository.ItemRepository;
import jejeongmin.MakeAnything.user.domain.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private File fileLib;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getRecentItems() {
        return itemRepository.findTop5ByOrderByCreatedAtDesc();
    }

    @Override
    public Item createItem(User user, ItemDto itemDto) {
        Item item = modelMapper.map(itemDto, Item.class);
        item.setUser(user);
        return itemRepository.save(item);
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new FileIsEmptyException();
        }
        return fileLib.uploadFile(file);
    }

}
