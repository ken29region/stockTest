package ken.stockTest.repositories.fake;

import ken.stockTest.entity.User;
import ken.stockTest.entity.UserRole;
import ken.stockTest.entity.UserStatus;
import ken.stockTest.repositories.Repository;
import ken.stockTest.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
//@Component
public class FakeUser implements UserRepository {

    private static List<User> users;

    static {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        users = new ArrayList<User>(){{
            add(User.builder()
                    .login("eg").hashPassword(passwordEncoder.encode("eg"))
                    .firstName("Egor").lastName("Klepikov").id(1l).userRole(UserRole.ADMIN).userStatus(UserStatus.ACTIVE).build());
            add(User.builder()
                    .firstName("Mikhael").lastName("Petrona").id(2l).userRole(UserRole.USER).userStatus(UserStatus.ACTIVE).build());
            add(User.builder()
                    .firstName("Mary").lastName("Jobs").id(3l).userRole(UserRole.USER).userStatus(UserStatus.ACTIVE).build());
        }};
    }

    @Override
    public Optional<User> findOneByLogin(String login) {
        return users.stream().filter(user -> user.getLogin().equals(login)).findFirst();
    }

    @Override
    public Long add(User user) {

        long id = users.size() + 1;
        user.setId(id);
        users.add(user);

        return id;
    }

    @Override
    public User getById(Long id) {
        return users.get(id.intValue());
    }

    @Override
    public void set(User user) {
        users.set(user.getId().intValue() ,user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }
}
