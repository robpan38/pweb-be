package robert.swag.pwebbe.dto.auth;

import lombok.Data;

@Data
public class SignUpRequest {

    private String firstName;
    private String email;
    private String password;
}
