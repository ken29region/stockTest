package ken.stockTest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ken.stockTest.entity.Category;
import ken.stockTest.repositories.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private RepositoryFacade globalRepo;

    @GetMapping("{id}")
    public String getCategory(@RequestParam(name = "id", value = "id", required = false)Long id){
        return gson.toJson(globalRepo.getCategoryById(id), Category.class);
    }

    @GetMapping
    public String getAllCategories(){
        return gson.toJson(globalRepo.getAllCategories(), List.class);
    }

    @PostMapping
    public String addCategory(@RequestBody String json){
        Category category = gson.fromJson(json, Category.class);
        globalRepo.addCategory(category);
        return json;
    }

    @PutMapping("{id}")
    public String setCategory(@PathVariable String id, @RequestBody String json){

        Category category = gson.fromJson(json, Category.class);
        category.setId(Long.parseLong(id));

        globalRepo.setCategory(category);
        return json;
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable String id, @RequestBody String json){

        Category category = gson.fromJson(json, Category.class);
        category.setId(Long.parseLong(id));

        globalRepo.deleteCategory(category);
    }

}
