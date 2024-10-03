package vivien.doingthigns.fantasyworldgenerator.config.jwt;

import java.util.ArrayList;

import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vivien.doingthigns.fantasyworldgenerator.config.repositories.UserRepository;
import vivien.doingthigns.fantasyworldgenerator.usermanagement.User;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                // Set to true as authorities are removed
                true, true, true, true,
                new ArrayList<>() // No roles/authorities
        );
    }
}
