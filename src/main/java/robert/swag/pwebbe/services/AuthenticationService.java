package robert.swag.pwebbe.services;

import robert.swag.pwebbe.dto.auth.JwtAuthenticationResponse;
import robert.swag.pwebbe.dto.auth.SignInRequest;
import robert.swag.pwebbe.dto.auth.SignUpRequest;
import robert.swag.pwebbe.entities.User;

public interface AuthenticationService {
    public User signUp(SignUpRequest signUpRequest);
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
