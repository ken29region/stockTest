package ken.stockTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories")
public class CategoryTableController {

    @GetMapping
    public String getCategoriesTable(){
        return "categories";
    }

}
