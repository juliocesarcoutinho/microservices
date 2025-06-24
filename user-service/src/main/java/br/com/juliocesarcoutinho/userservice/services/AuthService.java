package br.com.juliocesarcoutinho.userservice.services;
import br.com.juliocesarcoutinho.userservice.entities.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.juliocesarcoutinho.userservice.dtos.AccountCredentialsDTO;
import br.com.juliocesarcoutinho.userservice.dtos.TokenDTO;
import br.com.juliocesarcoutinho.userservice.repositories.UserRepository;
import br.com.juliocesarcoutinho.userservice.securities.JwtTokenProvider;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    // Implementation signIn
    public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO credentials) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.email(),
                        credentials.password()
                )
        );
        var user = userRepository.findByEmail(credentials.email());
        if (user == null) throw new UsernameNotFoundException("Email not found" + credentials.email());
        var tokenResponse = jwtTokenProvider.createAccessToken(credentials.email(), user.getRoles());
        return ResponseEntity.ok(tokenResponse);
    }

    // Implementation refresh
    public ResponseEntity<TokenDTO> refreshToken(String email, String refreshToken) {
        var user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("Email not found" + email);
        return ResponseEntity.ok(jwtTokenProvider.refreshToken(refreshToken));
    }
    
    public ResponseEntity<User> findMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("User not found: " + email);
        return ResponseEntity.ok(user);
    }
}