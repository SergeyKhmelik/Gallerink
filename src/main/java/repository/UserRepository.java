package repository;

import domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    boolean findByUsernameIsNull(String username);

}
