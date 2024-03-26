package robert.swag.pwebbe.dto.auth;

import lombok.Data;

@Data
public class SignInRequest {

    private String email;
    private String password;
}
