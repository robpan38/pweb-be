package robert.swag.pwebbe.services;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    public UserDetailsService userDetailsService();
    public String getRole(String username);
}
