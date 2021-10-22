package ITCEQConge.configuration.security;

import ITCEQConge.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private String username;
    private String password;
    private boolean enabled ;
    private boolean locked ;
    private GrantedAuthority authority;

    public UserDetails(User user) {
    this.username=user.getUsernameUser();
    this.password=user.getPasswordUser();
    this.enabled=user.isEnabled();
    this.locked=user.isLocked();
    this.authority= new SimpleGrantedAuthority("ROLE_" + user.getRoleUser().name());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
