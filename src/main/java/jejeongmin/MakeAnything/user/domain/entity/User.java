package jejeongmin.MakeAnything.user.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(length = 50, nullable = false)
    private String id;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    private Integer room;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String profileImage;

}
