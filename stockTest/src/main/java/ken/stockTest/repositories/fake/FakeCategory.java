package ken.stockTest.repositories.fake;

import ken.stockTest.entity.Category;
import ken.stockTest.repositories.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

//@Component
public class FakeCategory implements CategoryRepository {

    private static List<Category> categories;

    static {
        categories = new LinkedList<Category>(){{
            add(Category.builder().id(0l).name("oli").description("service fluids").count(6).build());
            add(Category.builder().id(1l).name("cars").description("Cars and vehicles").count(5).build());
            add(Category.builder().id(2l).name("parts").description("spare parts").count(7).build());
            add(Category.builder().id(3l).name("clothes").description("Different clothes").count(6).build());
            add(Category.builder().id(4l).name("accessories").description("Accessories and other things").count(6).build());
        }};
    }

    @Override
    public Long add(Category category) {
        Long id = (long)(categories.size());
        category.setId(id);
        categories.add(category);
        return id;
    }

    @Override
    public Integer productCountOfCategory(Long id) {
        return null;
    }

    @Override
    public Category getById(Long id) {
        return categories.stream().filter(
                category -> (id.equals(category.getId()))).findFirst().get();
    }

    @Override
    public void set(Category category) {
        categories.set(category.getId().intValue(), category);
    }

    @Override
    public void delete(Category category) {
        categories.remove(category);
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category findByName(String name) {
        return categories.stream().filter(category -> (category.getName().equals(name))).findFirst().get();
    }
}
