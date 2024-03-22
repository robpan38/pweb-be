package robert.swag.pwebbe.services;

import robert.swag.pwebbe.dto.JwtAuthenticationResponse;
import robert.swag.pwebbe.dto.SignInRequest;
import robert.swag.pwebbe.dto.SignUpRequest;
import robert.swag.pwebbe.entities.User;

public interface AuthenticationService {
    public User signUp(SignUpRequest signUpRequest);
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
