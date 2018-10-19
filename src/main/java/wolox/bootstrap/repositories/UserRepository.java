package wolox.bootstrap.repositories;

import org.springframework.data.repository.CrudRepository;
import wolox.bootstrap.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
