package com.greenacademy.api;

import com.greenacademy.config.JwtUtils;
import com.greenacademy.model.TokenRequest;
import com.greenacademy.model.TokenResponse;
import com.greenacademy.model.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthApi {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody TokenRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserAdapter userAdapter = (UserAdapter) authentication.getPrincipal();
        List<String> roles = userAdapter.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .toList();

        String token = jwtUtils.generateToken(userAdapter.getUsername(), roles);
//        String token = jwtUtils.generateToken(userAdapter.getUsername());
        TokenResponse response = new TokenResponse();
        response.setToken(token);
        response.setExpired(new Date().getTime() + jwtUtils.getExpiration());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserAdapter userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
        }

        String username = userDetails.getUsername();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ProfileResponse(username, roles));
    }

    // DTO trả về client, Java 16+ record
    record ProfileResponse(String username, List<String> roles) {}

}