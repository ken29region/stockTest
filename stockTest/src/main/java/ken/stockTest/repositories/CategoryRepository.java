package ken.stockTest.repositories;

import ken.stockTest.entity.Category;

import java.util.List;

public interface CategoryRepository extends Repository<Category> {

    List<Category> getAll();

    Category findByName(String name);

    Integer productCountOfCategory(Long id);

}
