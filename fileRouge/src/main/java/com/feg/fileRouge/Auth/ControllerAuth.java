package com.feg.fileRouge.Auth;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")   // /api/v1/auth/register
@RequiredArgsConstructor
public class ControllerAuth {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/autheticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AutheticateRequest request ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
