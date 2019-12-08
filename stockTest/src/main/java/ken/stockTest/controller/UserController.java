package ken.stockTest.controller;

import ken.stockTest.entity.User;
import ken.stockTest.repositories.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private RepositoryFacade globalRepo;

    @GetMapping("{all}")
    public List<User> getAllUser(){
        return globalRepo.getAllUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@RequestParam(value = "id", required = false)Long id){
        return globalRepo.getUserById(id);
    }

    @PostMapping
    public String saveUser(){
        return "users";
    }
}
