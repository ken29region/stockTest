package ken.stockTest.security.details;

import ken.stockTest.entity.User;
import ken.stockTest.entity.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String userRole = getUser().getUserRole().name();
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRole);

        return Collections.singletonList(grantedAuthority);
    }

    @Override
    public String getPassword() {
        return getUser().getHashPassword();
    }

    @Override
    public String getUsername() {
        return getUser().getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !getUser().getUserStatus().equals(UserStatus.BLOCKED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getUser().getUserStatus().equals(UserStatus.ACTIVE);
    }
}
