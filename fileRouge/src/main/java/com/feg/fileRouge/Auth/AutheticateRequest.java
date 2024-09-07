package com.feg.fileRouge.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class AutheticateRequest {
    private String email;
    private String motDePasse;
}
