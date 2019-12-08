package ken.stockTest.repositories.hibernate;

import ken.stockTest.entity.Category;
import ken.stockTest.entity.Product;
import ken.stockTest.repositories.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class ProductHiberRepo implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> getAll() {

        List<Product> products = new LinkedList<>();

        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();

            Query query = session.createSQLQuery("select * from product");
            ((NativeQuery) query).addEntity(Product.class);
            products = query.getResultList();

            transaction.commit();

            return products;
        }catch (Exception e){

        }

        return products;
    }

    @Override
    public List<Product> findProductsByCategoryName(String categoryName) {

        return getAll().stream()
                .filter(product -> product.getCategory().getName().equals(categoryName)).collect(Collectors.toList());
    }

    @Override
    public Long add(Product product) {

        Long id = product.getId();

        try(Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            id = (Long) session.save(product);
            transaction.commit();

        }

        return id;
    }

    @Override
    public Product getById(Long id) {

        Product product = new Product();

        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();
            product = session.get(Product.class, id);
            transaction.commit();

        }

        return product;
    }

    @Override
    public void set(Product product) {

        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        }

    }

    @Override
    public void delete(Product product) {

        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();

        }

    }
}