package ken.stockTest.repositories.hibernate;

import com.zaxxer.hikari.HikariDataSource;
import ken.stockTest.entity.Category;
import ken.stockTest.entity.Product;
import ken.stockTest.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(){

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setAnnotatedClasses(
                Category.class, User.class, Product.class
        );
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public DataSource dataSource(){

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        dataSource.addDataSourceProperty("url", "jdbc:postgresql://localhost:5432/stockdb");
        dataSource.addDataSourceProperty("user", "postgres");
        dataSource.addDataSourceProperty("password", "root");

        return dataSource;
    }

    Properties hibernateProperties(){
        return new Properties(){
            {
                setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
                setProperty("hibernate.show_sql", "true");
                setProperty("hibernate.hbm2ddl.auto", "create");
                setProperty("org.hibernate.tool.hbm2ddl", "create");
                setProperty("hibernate.hbm2ddl.import_files", "initial_data.sql");
                setProperty("hibernate.id.new_generator_mappings", "false");
            }
        };
    }


}
