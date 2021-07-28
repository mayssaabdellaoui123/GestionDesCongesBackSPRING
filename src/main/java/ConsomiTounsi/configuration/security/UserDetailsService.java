package ConsomiTounsi.configuration.security;

import ConsomiTounsi.entities.User;
import ConsomiTounsi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = ur.findByUsernameUser(s);
        user.orElseThrow( () -> new UsernameNotFoundException(s + "not found"));
        return user.map(ConsomiTounsi.configuration.security.UserDetails::new).get();
    }

}
