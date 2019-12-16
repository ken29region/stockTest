package ken.stockTest.repositories;

import ken.stockTest.entity.Category;
import ken.stockTest.entity.Product;
import ken.stockTest.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@ComponentScan("ken.stockTest.repositories")
public class RepositoryFacade {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public static SessionFactory sessionFactory;

    @Autowired
    public static void setSessionFactory(SessionFactory sessionFactory) {
        RepositoryFacade.sessionFactory = sessionFactory;
    }

    public Long addUser(User user) {
        return userRepository.add(user);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public Optional<User> findOneByLogin(String login) {
        return userRepository.findOneByLogin(login);
    }

    public void setUser(User user) {
        userRepository.set(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.getAll();
    }

    public Long addCategory(Category category) {
        return categoryRepository.add(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.getById(id);
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public void setCategory(Category category) {
        categoryRepository.set(category);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    public List<Product> findProductsByCategoryName(String categoryName) {
        return productRepository.findProductsByCategoryName(categoryName);
    }

    public List<Product> findLimitSortedBySortProductStartWith
            (Integer startPosition, Integer limit, Integer categoryId, String sort) {
        return productRepository.findLimitSortedBySortProductStartWith(startPosition, limit, categoryId, sort);
    }

    public Long addProduct(Product product) {
        return productRepository.add(product);
    }

    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    public void setProduct(Product product) {
        productRepository.set(product);
    }

    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }
}
