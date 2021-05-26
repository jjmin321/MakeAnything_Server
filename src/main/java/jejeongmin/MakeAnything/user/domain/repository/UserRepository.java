package jejeongmin.MakeAnything.user.domain.repository;

import jejeongmin.MakeAnything.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(String id);
}
