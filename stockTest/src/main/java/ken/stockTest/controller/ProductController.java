package ken.stockTest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ken.stockTest.entity.Category;
import ken.stockTest.entity.Product;
import ken.stockTest.repositories.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {

    private Integer startPosition = 0;

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private RepositoryFacade globalRepo;

    @GetMapping("{id}")
    public String getProduct(
            @RequestParam Map<String, String> requestParams){

        String productId = requestParams.get("id");
        Long id = Long.parseLong(productId);

        return gson.toJson(globalRepo.getProductById(id), Product.class);
    }

    @GetMapping
    public String getAllProducts(@RequestParam Map<String, String> requestParam){

        return gson.toJson(globalRepo
                .getAllProducts(), List.class);
    }

    @GetMapping("nextPage")
    public String getNextPage(@RequestParam Map<String, String> requestParam){

        String limitCandidate = requestParam.get("limit");

        Integer limit = checkLimitOrReturnDefaultValue(limitCandidate);

        startPosition += limit;
        requestParam.put("startPosition", startPosition.toString());

        return getProductsByPageAndSort(requestParam);
    }

    @GetMapping("previousPage")
    public String getPreviousPage(@RequestParam Map<String, String> requestParam){

        String limitCandidate = requestParam.get("limit");

        Integer limit = checkLimitOrReturnDefaultValue(limitCandidate);

        startPosition = startPosition - limit;
        requestParam.put("startPosition", startPosition.toString());

        return getProductsByPageAndSort(requestParam);
    }

    private Integer checkLimitOrReturnDefaultValue(String limitCandidate){
        Integer limit;
        if((limitCandidate != null)){
            limit = Integer.parseInt(limitCandidate);
        } else {
            limit = 10;
        }
        return limit;
    }

    @GetMapping("/page")
    public String getProductsByPageAndSort(@RequestParam Map<String, String> requestParam){

        String limitCandidate = requestParam.get("limit");
        String catName = requestParam.get("category");
        String sortCandidate = requestParam.get("sort");
        String startPositionCandidate = requestParam.get("startPosition");

        Integer limit = 10;
        Integer categoryId = 1;
        String sort = "id";

        if(startPositionCandidate != null){
            startPosition = Integer.parseInt(startPositionCandidate);
            if(startPosition < 0){
                startPosition = 0;
            }
        } else {
            startPosition = 0;
        }
        if(limitCandidate != null){
            limit = Integer.parseInt(limitCandidate);
        }
        if(catName != null) {
            if(catName.equals("all")){
                categoryId = 0;
            }else {
                categoryId = globalRepo.findCategoryByName(catName).getId().intValue();
            }
        }
        if(sortCandidate != null){
            sort = sortCandidate;
        }

        System.out.println(requestParam);
        System.out.println("cat id: " + categoryId + " limit: " + limit + " sort: " + sort);

        return gson.toJson(globalRepo
                .findLimitSortedBySortProductStartWith(startPosition, limit, categoryId, sort), List.class);
    }

    @GetMapping("/catName")
    public String getProductsByCategoryName(@RequestParam(name = "catName", value = "catName")String catName){
        if(catName.equals("all")){
            return getAllProducts(new HashMap<String, String>(){{put("displayElement", "10");}});
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