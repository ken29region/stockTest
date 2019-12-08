package ken.stockTest.repositories.hibernate;

import ken.stockTest.entity.Category;
import ken.stockTest.entity.Product;
import ken.stockTest.repositories.CategoryRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryHiberRepo implements CategoryRepository {

    @Autowired
    Environment environment;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<Category> query;

        query = session.createSQLQuery("select * from category");
        ((NativeQuery<Category>) query).addEntity(Category.class);
        List<Category> categories = query.getResultList();

        for (Category category : categories)
            category.setCount(productCountOfCategory(category.getId()));

        transaction.commit();
        session.close();

        return categories;
    }

    @Override
    public Integer productCountOfCategory(Long id) {

        Integer count = 0;

        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();
            Query<Product> query = session
                    .createSQLQuery("select * from product where categoryid = :catId ")
                    .addEntity(Product.class)
                    .setParameter("catId", id);
            List<Product> products = query.getResultList();
            count = products.size();
            transaction.commit();

        }

        return count;
    }

    @Override
    public Category findByName(String name) {
        Category result =
                getAll().stream().filter(category -> category.getName().equals(name)).findFirst().get();
        result.setCount(productCountOfCategory(result.getId()));

        return result;
    }

    @Override
    public Long add(Category category) {

        Long id = null;

        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            id = (Long) session.save(category);
            transaction.commit();
        }

        return id;
    }

    @Override
    public Category getById(Long id) {

        Category category = null;

        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();
            category = session.get(Category.class, id);

            System.out.println(category);

            category.setCount(productCountOfCategory(id));
        }

        return category;
    }

    @Override
    public void set(Category category) {
        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();
            category.setCount(productCountOfCategory(category.getId()));
            session.update(category);
            transaction.commit();
        }
    }

    @Override
    public void delete(Category category) {

        try(Session session = sessionFactory.openSession()){

            Transaction transaction = session.beginTransaction();

            session.createSQLQuery("delete from product where categoryid =:catId")
                    .addEntity(Product.class)
                    .setParameter("catId", category.getId()).executeUpdate();

            session.delete(category);

            transaction.commit();
        }
    }
}
