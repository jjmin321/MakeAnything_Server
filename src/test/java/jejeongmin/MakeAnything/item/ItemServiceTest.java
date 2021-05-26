package jejeongmin.MakeAnything.item;

import jejeongmin.MakeAnything.common.enums.ItemType;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import jejeongmin.MakeAnything.item.domain.repository.ItemRepository;
import jejeongmin.MakeAnything.user.domain.entity.User;
import jejeongmin.MakeAnything.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class ItemServiceTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createItem() {
        List<User> user = userRepository.findAll();

        Item customItem = Item.builder()
                .type(ItemType.CUSTOM)
                .user(user.get(0))
                .name("판매 요청 물품")
                .description("해당 물품은 판매 요청 물품에 해당하는 상품입니다")
                .price(5000)
                .thumbnail("판매요청1.jpg")
                .build();
        itemRepository.save(customItem);
        Item createdCustomItem = itemRepository.findByName(customItem.getName());

        Item usedItem = Item.builder()
                .type(ItemType.USED)
                .user(user.get(1))
                .name("중고 물품")
                .description("해당 물품은 중고 물품에 해당하는 상품입니다")
                .price(10000)
                .thumbnail("중고1.jpg")
                .build();
        itemRepository.save(usedItem);
        Item createdUsedItem = itemRepository.findByName(usedItem.getName());

        Item talentItem = Item.builder()
                .type(ItemType.TALENT)
                .user(user.get(2))
                .name("재능 물품")
                .description("해당 물품은 재능 물품에 해당하는 상품입니다")
                .price(30000)
                .thumbnail("재능물품1.jpeg")
                .build();
        itemRepository.save(talentItem);
        Item createdTalentItem = itemRepository.findByName(talentItem.getName());

        Assertions.assertEquals(customItem, createdCustomItem);
        Assertions.assertEquals(usedItem, createdUsedItem);
        Assertions.assertEquals(talentItem, createdTalentItem);
    }

}
