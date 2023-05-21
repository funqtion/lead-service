package com.dakani.leadService.persistence.repository;

import com.dakani.leadService.persistence.entity.Token;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class TokenRepository {

    private final EntityManager entityManager;

    public List<Token> getTokensByToken(String token) {
        return entityManager
                .createQuery("SELECT t FROM Token AS t WHERE t.token = :token and t.expiresAt >= CURRENT_TIMESTAMP", Token.class)
                .setParameter("token", token)
                .getResultList();
    }
    public List<Token> getTokensByPathAndToken(String path, String token) {
        return entityManager
                .createQuery("SELECT t FROM Token AS t WHERE t.token = ':token' and t.path = :path and t.expiresAt >= CURRENT_TIMESTAMP", Token.class)
                .setParameter("token", token)
                .setParameter("path", path)
                .getResultList();
    }
}
