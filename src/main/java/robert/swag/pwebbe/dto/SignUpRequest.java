package robert.swag.pwebbe.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String firstName;
    private String email;
    private String password;
}
