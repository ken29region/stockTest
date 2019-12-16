package ken.stockTest.repositories;

import ken.stockTest.entity.Product;

import java.util.List;

public interface ProductRepository extends Repository<Product> {

    List<Product> getAll();

    List<Product> findProductsByCategoryName(String categoryName);

    List<Product> findLimitSortedBySortProductStartWith
            (Integer startPosition, Integer limit, Integer categoryId, String sort);

}
