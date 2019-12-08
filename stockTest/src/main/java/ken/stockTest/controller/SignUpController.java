package ken.stockTest.controller;

import ken.stockTest.entity.UserForm;
import ken.stockTest.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(){
        return "signUp";
    }

    @PostMapping
    public String signUp(UserForm userForm){

        signUpService.signUp(userForm);

        return "redirect:/login";
    }
}
