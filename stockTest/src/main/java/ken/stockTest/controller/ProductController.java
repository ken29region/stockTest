package ken.stockTest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ken.stockTest.entity.Category;
import ken.stockTest.entity.Product;
import ken.stockTest.repositories.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private RepositoryFacade globalRepo;

    @GetMapping("{id}")
    public String getProduct(@RequestParam(name = "id", value = "id", required = false)Long id){
        return gson.toJson(globalRepo.getProductById(id), Product.class);
    }

    @GetMapping
    public String getAllProducts(){
        return gson.toJson(globalRepo.getAllProducts(), List.class);
    }

    @GetMapping("/catName")
    public String getProductsByCategoryName(@RequestParam(name = "catName", value = "catName")String catName){
        if(catName.equals("all")){
            return getAllProducts();
        }
        return gson.toJson(globalRepo.findProductsByCategoryName(catName), List.class);
    }

    @PostMapping
    public String addProduct(@RequestBody String json){
        System.out.println(json);
        Product product = gson.fromJson(json, Product.class);
        Category category = globalRepo.findCategoryByName(product.getCategory().getName());
        product.setCategory(category);
        System.out.println(product.getCategory());
        globalRepo.addProduct(product);
        return json;
    }

    @PutMapping("{id}")
    public String setProduct(@PathVariable String id, @RequestBody String json){

        Product product = gson.fromJson(json, Product.class);
        product.setId(Long.parseLong(id));

        globalRepo.setProduct(product);
        return json;
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable String id, @RequestBody String json){

        Product product = gson.fromJson(json, Product.class);
        product.setId(Long.parseLong(id));

        globalRepo.deleteProduct(product);
    }
}