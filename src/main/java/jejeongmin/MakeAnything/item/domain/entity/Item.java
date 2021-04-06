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
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "user_idx")
    private User user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "file")
    private String file;
}
