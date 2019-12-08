package ken.stockTest.repositories.hibernate;

import ken.stockTest.entity.User;
import ken.stockTest.repositories.RepositoryFacade;
import ken.stockTest.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Primary
public class UserHiberRepo implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<User> findOneByLogin(String login) {

        User userCandidate = getAll().stream().filter(user -> user.getLogin().equals(login)).findFirst().get();

        System.out.println(userCandidate);

        return Optional.of(userCandidate);
    }

    @Override
    public Long add(User user) {

        Long id = null;

        try(Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            id = (Long) session.save(user);
            transaction.commit();

        }

        return id;
    }

    @Override
    public User getById(Long id) {
        return sessionFactory
                .openSession().get(User.class, id);
    }

    @Override
    public void set(User user) {
        Session session = sessionFactory
                .openSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(User user) {
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAll() {

        List<User> users = new LinkedList<>();

        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createSQLQuery("select * from users")
                    .addEntity(User.class);
            users = query.getResultList();
            transaction.commit();
        }
        return users;
    }
}
