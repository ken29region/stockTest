package ken.stockTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductPageController {

    @GetMapping
    public String getProductsTable(){
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(){
        return "addProduct";
    }

}