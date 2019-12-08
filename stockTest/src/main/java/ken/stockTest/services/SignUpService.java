package ken.stockTest.services;

import ken.stockTest.entity.LoginForm;
import ken.stockTest.entity.UserForm;

public interface SignUpService {
    void signUp(UserForm userForm);
    boolean login(LoginForm loginForm);
}
