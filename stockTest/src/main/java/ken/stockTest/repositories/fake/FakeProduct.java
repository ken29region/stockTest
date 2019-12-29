package ken.stockTest.repositories.fake;

import ken.stockTest.entity.Product;
import ken.stockTest.repositories.CategoryRepository;
import ken.stockTest.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
public class FakeProduct implements ProductRepository {

    private static List<Product> products;

    static{

        CategoryRepository categoryRepository = new FakeCategory();

        products = new ArrayList<Product>(){{
            add(Product.builder()
                    .id(0l)
                    .category(categoryRepository.getById(0l))
                    .name("elf 0W-30")
                    .description("engine oil")
                    .count(200.0)
                    .price(650.0)
                    .imageSrc("no image")
                    .build());
            add(Product.builder()
                    .id(1l)
                    .category(categoryRepository.getById(0l))
                    .name("(R+M)/2-89")
                    .description("gasoline")
                    .count(248.0)
                    .price(47.56)
                    .imageSrc("no image")
                    .build());
            add(Product.builder()
                    .id(2l)
                    .category(categoryRepository.getById(0l))
                    .name("(R+M)/2-87")
                    .description("gasoline")
                    .count(134.0)
                    .price(44.31)
                    .imageSrc("no image")
                    .build());
            add(Product.builder()
                    .id(3l)
                    .category(categoryRepository.getById(1l))
                    .name("Arkana")
                    .description("new car")
                    .count(10.0)
                    .price(1000000.0)
                    .imageSrc("no image")
                    .build());
            add(Product.builder()
                    .id(4l)
                    .category(categoryRepository.getById(1l))
                    .name("Duster")
                    .description("car")
                    .count(9.0)
                    .price(900000.0)
                    .imageSrc("no image")
                    .build());
        }};
    }


    @Override
    public List<Product> findLimitSortedBySortProductStartWith
            (Integer startPosition, Integer limit, Integer category, String sort) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public List<Product> findProductsByCategoryName(String categoryName) {
        return products.stream().filter(product -> product.getCategory().getName().equals(categoryName)).collect(Collectors.toList());
    }

    @Override
    public Long add(Product product) {
        long id = products.size();
        product.setId(id);
        products.add(product);
        return id;
    }

    @Override
    public Product getById(Long id) {
        return products.get(id.intValue());
    }

    @Override
    public void set(Product product) {
        products.set(product.getId().intValue(), product);
    }

    @Override
    public void delete(Product product) {
        products.remove(product);
    }
}