package co.privacymap.api.config.security;

import co.privacymap.api.model.Client;
import co.privacymap.api.repository.ClientRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private ClientRepository clientRepository;

    public AuthenticationTokenFilter(TokenService tokenService, ClientRepository repository) {
        this.tokenService = tokenService;
        this.clientRepository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recoverToken(request);
        boolean tokenIsValid = tokenService.isTokenValid(token);

        if(tokenIsValid){
            authenticateClient(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        String idClient = tokenService.getIdClient(token);
        System.out.println("String ID" + idClient);
        UUID clientUUID = UUID.fromString(idClient);
        System.out.println("UUID " + clientUUID);
        Client client = clientRepository.findById(clientUUID).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }

        return token.substring(7, token.length());
    }
}
