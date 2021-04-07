package jejeongmin.MakeAnything.user.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "member")
@Getter
@Setter
public class User {

    @Id
    @Column(length = 50, nullable = false)
    private String id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String profileImage;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    private Integer room;

}
