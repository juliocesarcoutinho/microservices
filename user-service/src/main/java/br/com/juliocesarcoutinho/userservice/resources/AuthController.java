package br.com.juliocesarcoutinho.userservice.resources;

import io.swagger.v3.oas.annotations.Hidden;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.juliocesarcoutinho.userservice.dtos.AccountCredentialsDTO;
import br.com.juliocesarcoutinho.userservice.resources.docs.AuthControllerDocs;
import br.com.juliocesarcoutinho.userservice.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication", description = "Endpoints for authentication")
@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Implement the signIn endpoint
    @PostMapping("/signin")
    @Override
    public ResponseEntity<?> signIn(@RequestBody AccountCredentialsDTO credentials) {

        if (credentialsIsInvalid(credentials))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client credentials");
        var token = authService.signIn(credentials);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client credentials");

        return ResponseEntity.ok().body(token);
    }

    // Implement the refresh endpoint
    @PutMapping("/refresh/{email}")
    @Override
    public ResponseEntity<?> refresh(@PathVariable("email") String email,
                                     @RequestHeader("Authorization") String refreshToken) {
        if (parametersAreInvalid(email, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client credentials");
        var token = authService.refreshToken(email, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client credentials");
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @Hidden
    public ResponseEntity<?> findMe() {
        var user = authService.findMe();
        return ResponseEntity.ok(user);
    }

    private boolean parametersAreInvalid(String email, String refreshToken) {
        return StringUtils.isBlank(email) || StringUtils.isBlank(refreshToken);
    }

    private static boolean credentialsIsInvalid(AccountCredentialsDTO credentials) {
        return credentials == null || StringUtils.isBlank(credentials.password())
                || StringUtils.isBlank(credentials.email());
    }
}
