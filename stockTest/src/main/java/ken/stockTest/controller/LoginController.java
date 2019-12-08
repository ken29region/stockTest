package ken.stockTest.controller;

import ken.stockTest.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping
    public String getLoginPage(Authentication authentication, ModelMap model, HttpServletRequest request){

        if(authentication != null)
            return "redirect:/products";

        if(request.getParameterMap().containsKey("error")){
            model.addAttribute("error", true);
        }
        return "login";
    }
}
