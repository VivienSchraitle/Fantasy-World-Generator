/*package vivien.doingthigns.fantasyworldgenerator.config.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vivien.doingthigns.fantasyworldgenerator.config.jwt.JwtTokenProvider;
import vivien.doingthigns.fantasyworldgenerator.usermanagement.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String password = userData.get("password");

        logger.info("Received login request with username: {}", username);

        try {
            // Step 1: Authenticate the user
            logger.info("Authenticating user with username: {}", username);
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            // Step 2: Check if authentication was successful
            if (authentication.isAuthenticated()) {
                logger.info("Authentication successful for user: {}", username);

                // Step 3: Generate JWT token
                String token = jwtTokenProvider.generateJwtToken(authentication);
                logger.info("Generated JWT token for user: {}", username);

                return ResponseEntity.ok("Login successful. Token: " + token);
            } else {
                logger.warn("Authentication failed for user: {}", username);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login failed");
            }
        } catch (AuthenticationException e) {
            logger.error("AuthenticationException for user: {}: {}", username, e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login failed: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> userData) {
        logger.info("Received registration request with data: {}", userData);

        return userService.registerUser(userData);
    }
}

*/