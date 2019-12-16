package ken.stockTest.services;

import ken.stockTest.entity.Product;
import ken.stockTest.repositories.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductPageServiceImpl implements ProductPageService {

    @Autowired
    private RepositoryFacade globalRepo;


    @Override
    public List<Product> getProductPage(int pageNumber) {
        return null;
    }

    private List<Page> formPages(Integer splitter){
        List<Page> pages = new LinkedList<>();

        if(splitter == null){
            splitter = 20;
        }

        Iterator<Product> iterator = getProducts().iterator();
        List<Product> allProd = getProducts();

        while (iterator.hasNext()) {
            Page page = Page.builder().products(allProd.subList(0, splitter)).build();
            pages.add(page);


        }

        return pages;
    }

    private List<Product> getProducts(){
        return globalRepo.getAllProducts();
    }
}
