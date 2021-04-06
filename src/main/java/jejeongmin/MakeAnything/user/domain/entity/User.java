package jejeongmin.MakeAnything.user.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "member")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 25, nullable = false, unique = true)
    private String phone;
}
