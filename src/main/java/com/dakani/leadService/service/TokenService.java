package com.dakani.leadService.service;

import com.dakani.leadService.persistence.entity.Token;
import com.dakani.leadService.persistence.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j(topic = "leadService")
public class TokenService {
    private final TokenRepository tokenRepository;

    public boolean isTokenValid(String path, String authHeader) {
        if (!authHeader.startsWith("Bearer ") || authHeader.split(" ")[1].length() != 64) {
            return false;
        }
        String token = authHeader.split(" ")[1];
        List<Token> validTokens = tokenRepository.getTokensByToken(token);
        Optional<Token> maybeValidToken = validTokens
                .stream()
                .filter(validToken -> (validToken.getPath().equals(path) && validToken.getToken().equals(token)) || validToken.isAdmin())
                .findAny();


        return maybeValidToken.isPresent();
    }
}
