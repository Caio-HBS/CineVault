package com.caiohbs.restful.cinevault.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @PostMapping("/register")
    public ResponseEntity registerEndpoint() {
        // TODO: this register view.
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity authenticateEndpoint() {
        // TODO: this authenticate view.
        return null;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity refreshJwtToken() {
        // TODO: this refresh token view.
        return null;
    }

}
