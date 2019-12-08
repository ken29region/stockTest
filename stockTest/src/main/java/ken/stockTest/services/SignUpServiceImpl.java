package ken.stockTest.services;

import ken.stockTest.entity.*;
import ken.stockTest.repositories.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private RepositoryFacade globalRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .login(userForm.getLogin())
                .hashPassword(hashPassword)
                .userRole(UserRole.USER)
                .userStatus(UserStatus.ACTIVE)
                .build();

        globalRepo.addUser(user);

        System.out.println(globalRepo.getAllUsers());
    }

    public boolean login(LoginForm loginForm){

        String hashPasswordCandidate = passwordEncoder.encode(loginForm.getPassword());

        String loginCandidate = loginForm.getLogin();

        User user = globalRepo.findOneByLogin(loginCandidate).get();

        if(user != null){
            if(user.getHashPassword().equals(hashPasswordCandidate))
                return true;
        }

        return false;
    }
}
