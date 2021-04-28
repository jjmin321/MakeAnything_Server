package jejeongmin.MakeAnything.item.domain.entity;

import jejeongmin.MakeAnything.common.enums.ItemType;
import jejeongmin.MakeAnything.user.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "item")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Enumerated(EnumType.ORDINAL)
    private ItemType type;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "id")
    private User user;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(length = 50, nullable = false)
    private String thumbnail;

    @CreationTimestamp
    private LocalDateTime createdAt; // Date(UTC Issue in java) -> LocalDateTime

}
