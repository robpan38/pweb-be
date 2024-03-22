package robert.swag.pwebbe.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import robert.swag.pwebbe.dto.JwtAuthenticationResponse;
import robert.swag.pwebbe.dto.SignInRequest;
import robert.swag.pwebbe.dto.SignUpRequest;
import robert.swag.pwebbe.entities.Role;
import robert.swag.pwebbe.entities.User;
import robert.swag.pwebbe.repositories.UserRepository;
import robert.swag.pwebbe.services.AuthenticationService;
import robert.swag.pwebbe.services.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
        return user;
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String token = jwtService.generateToken(user);
        jwtAuthenticationResponse.setToken(token);

        return jwtAuthenticationResponse;
    }
}
