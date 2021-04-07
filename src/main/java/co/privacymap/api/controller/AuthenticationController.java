package co.privacymap.api.controller;

import co.privacymap.api.config.security.TokenService;
import co.privacymap.api.model.dto.LoginForm;
import co.privacymap.api.model.dto.TokenOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Profile("prod")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenOutput> authenticate(@RequestBody @Valid LoginForm form){
        UsernamePasswordAuthenticationToken login = form.converter();

        try{
            Authentication authentication = authenticationManager.authenticate(login);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok(new TokenOutput(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }


    }
}
