package org.example.productservice.security.services;

import org.example.productservice.security.dtos.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthenticationService {
    private final RestTemplate restTemplate;

    public AuthenticationService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public boolean authentiacate(String token){
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(("http://localhost:9000/users/validate/" + token), null, User.class );

        if (responseEntity.getBody() != null) {
            return true;
        }
        return false;
    }
}
