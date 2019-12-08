package ken.stockTest.repositories;

import ken.stockTest.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends Repository<User>{

    List<User> getAll();

    Optional<User> findOneByLogin(String login);
}
