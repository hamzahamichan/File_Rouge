package com.feg.fileRouge.Auth;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")   // /api/v1/auth/register
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ControllerAuth {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request ){
            if (request.getEmail()==null || request.getEmail().isEmpty()){
            return ResponseEntity.badRequest().build();
        } // Vérifier si un client avec cet email existe déjà
        if (authenticationService.emailExists(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthenticationResponse("Cet email est déjà utilisé."));
        }
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.register(request));
    }

    @PostMapping("/autheticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@Valid @RequestBody AutheticateRequest request ){

        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    // Ajout de gestionnaires d'exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((first, second) -> first + ", " + second)
                .orElse("Erreur de validation");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
