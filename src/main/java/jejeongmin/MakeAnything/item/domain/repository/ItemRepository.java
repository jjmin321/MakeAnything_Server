package jejeongmin.MakeAnything.item.domain.repository;

import jejeongmin.MakeAnything.common.enums.ItemType;
import jejeongmin.MakeAnything.item.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    public Item findByName(String name);
    public List<Item> findAllByNameContaining(String name);
    public List<Item> findAllByOrderByCreatedAt();
    public List<Item> findAllByOrderByCreatedAtDesc();
    public List<Item> findAllByOrderByPrice();
    public List<Item> findAllByOrderByPriceDesc();
    public List<Item> findAllByType(ItemType type);
    public List<Item> findTop5ByTypeOrderByCreatedAtDesc(ItemType type);
}
