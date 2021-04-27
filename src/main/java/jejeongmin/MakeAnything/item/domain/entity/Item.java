package jejeongmin.MakeAnything.item.domain.entity;

import jejeongmin.MakeAnything.user.domain.entity.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity(name = "item")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

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

    @Column(length = 10, nullable = false)
    private String type;
}
