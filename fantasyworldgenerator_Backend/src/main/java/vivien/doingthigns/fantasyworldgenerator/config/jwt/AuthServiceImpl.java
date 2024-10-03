package vivien.doingthigns.fantasyworldgenerator.config.jwt;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.Optional;



import vivien.doingthigns.fantasyworldgenerator.config.repositories.UserRepository;
import vivien.doingthigns.fantasyworldgenerator.usermanagement.User;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
public String login(LoginDto loginDto) {
    try {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate Token
        String token = jwtTokenProvider.generateToken(authentication);

        return token;

    } catch (BadCredentialsException e) {
        // Return specific message for bad credentials
        throw new RuntimeException("Invalid username or password", e);
    } catch (DisabledException e) {
        // Handle if user is disabled (optional)
        throw new RuntimeException("User account is disabled", e);
    } catch (LockedException e) {
        // Handle if user account is locked (optional)
        throw new RuntimeException("User account is locked", e);
    } catch (Exception e) {
        // General server error
        throw new RuntimeException("An error occurred while processing the login", e);
    }
}

    @Override
    public String register(LoginDto loginDto) {
        // Check if user already exists
        Optional<User> existingUser = userRepository.findByUsername(loginDto.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username is already taken");
        }

        // Hash the password before saving
        String hashedPassword = SpringSecurityConfig.passwordEncoder().encode(loginDto.getPassword());

        // Create new user and save to database
        User newUser = new User(loginDto.getUsername(), hashedPassword);
        userRepository.save(newUser); // Save user in database

        // Authenticate the newly created user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword() // This password will be matched after encoding
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate Token
        return jwtTokenProvider.generateToken(authentication);
    }
}