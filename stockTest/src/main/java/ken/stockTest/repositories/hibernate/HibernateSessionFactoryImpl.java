package ken.stockTest.repositories.hibernate;

import ken.stockTest.entity.Category;
import ken.stockTest.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionFactoryImpl {

    private static SessionFactory sessionFactory;

    public HibernateSessionFactoryImpl() {
    }

    /*@Bean
    @Primary
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(HibernateConfig.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Category.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }*/
}
