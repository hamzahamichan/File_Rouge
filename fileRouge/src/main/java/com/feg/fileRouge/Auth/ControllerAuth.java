package com.feg.fileRouge.Auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")   // /api/v1/auth/register
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ControllerAuth {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request ){
            if (request.getEmail()==null || request.getEmail().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
            else
                return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/autheticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AutheticateRequest request ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
