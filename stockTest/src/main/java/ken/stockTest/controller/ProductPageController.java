package ken.stockTest.controller;

import ken.stockTest.services.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductPageController {

    @Autowired
    private ProductPageService pageService;

    @GetMapping
    public String getProductsTable(){
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(){
        return "addProduct";
    }

}