package ken.stockTest.repositories;

import ken.stockTest.entity.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {

    List<Product> getAll();

    List<Product> findProductsByCategoryName(String categoryName);

}
