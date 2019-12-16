package ken.stockTest.services;

import ken.stockTest.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface ProductPageService {

    List<Product> getProductPage(int n);

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Page{

    private List<Product> products;

}
