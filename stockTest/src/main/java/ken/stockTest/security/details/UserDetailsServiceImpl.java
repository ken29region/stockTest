package ken.stockTest.security.details;

import ken.stockTest.repositories.RepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RepositoryFacade globalRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return new UserDetailsImpl(globalRepository.findOneByLogin(login).orElseThrow(IllegalArgumentException::new));
    }
}
