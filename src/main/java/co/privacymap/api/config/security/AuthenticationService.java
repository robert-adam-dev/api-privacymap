package co.privacymap.api.config.security;

import co.privacymap.api.model.Client;
import co.privacymap.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional <Client> client = userRepository.findByEmail(username);

        if(client.isPresent()){
            return client.get();
        }

        throw new UsernameNotFoundException("The username is invalid.");
    }
}
