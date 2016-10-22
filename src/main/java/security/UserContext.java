package security;

import com.google.common.collect.Lists;
import domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserContext implements UserDetails {

    private User user;

    public UserContext(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Lists.newArrayList(new SimpleGrantedAuthority(user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || o != null && o instanceof UserContext
                && Objects.equals(user, ((UserContext) o).user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user);
    }

    @Override
    public String toString() {
        return "UserContext{" +
                "user=" + user +
                '}';
    }


}
