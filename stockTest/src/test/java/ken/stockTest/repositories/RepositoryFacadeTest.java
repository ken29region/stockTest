package ken.stockTest.repositories;

import ken.stockTest.entity.Product;
import ken.stockTest.entity.User;
import ken.stockTest.repositories.hibernate.HibernateConfig;
import ken.stockTest.repositories.hibernate.UserHiberRepo;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.persistence.PersistenceContext;

//@ContextConfiguration(classes = {HibernateConfig.class})
public class RepositoryFacadeTest extends AbstractJUnit4SpringContextTests {

    Configuration configuration = new Configuration().addAnnotatedClass(HibernateConfig.class).configure();

    SessionFactory sessionFactory = configuration.buildSessionFactory();

    private RepositoryFacade globalRepo = RepositoryFacade.builder()
            .userRepository(UserHiberRepo.builder()
                    .sessionFactory(sessionFactory)
                    .build())
            .build();

    @Test
    public void addUser() {

        User user = User.builder()
                .firstName("Alexey")
                .lastName("Chuprov")
                .build();

        System.out.println(user);
        globalRepo.addUser(user);

    }


    @Test
    public void getUserById() {
        System.out.println(globalRepo.getUserById(2l));
    }

    @Test
    public void setUser() {

        User user = User.builder()
                .id(1l)
                .firstName("Mike")
                .lastName("Klepikov")
                .build();

        globalRepo.setUser(user);
    }

    @Test
    public void deleteUser() {

        User user = User.builder()
                .id(1l)
                .firstName("Mike")
                .lastName("Klepikov")
                .build();

        globalRepo.deleteUser(user);
    }

    @Test
    public void getCategoryByName(){

    }

    @Test
    public void getProductsByCategoryName(){

        for(Product product : globalRepo.findProductsByCategoryName("oli")){
            System.out.println(product);
        }
    }
}