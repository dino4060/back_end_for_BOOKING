package ute.mobile.back_end_for_BOOKING.business;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.common.exception.NotFoundE;
import ute.mobile.back_end_for_BOOKING.models.Token;
import ute.mobile.back_end_for_BOOKING.models.User;
import ute.mobile.back_end_for_BOOKING.models.repositories.TokenRepo;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TokenService {

    TokenRepo tokenRepo;

    public Token getByUserId(Long userId) {
        return this.tokenRepo.findByUserId(userId)
                .orElseThrow(() -> new NotFoundE("User not found"));
    }

    public Optional<Token> hasRefreshToken(String refreshToken, Long userId) {
        Token token = this.getByUserId(userId);

        if (!refreshToken.equals(token.getRefreshToken()))
            return Optional.empty();

        return Optional.of(token);
    }

    public void createToken(User user) {
        Token token = Token.createToken(user);
        this.tokenRepo.save(token);
    }

    public void updateRefreshToken(String refreshToken, Instant refreshTokenExpiry, Long userId) {
        Token token = this.getByUserId(userId);

        token.updateRefreshToken(refreshToken, refreshTokenExpiry);
        this.tokenRepo.save(token);
    }

    public void updateRefreshToken(String refreshToken, Instant refreshTokenExpiry, Token token) {
        token.updateRefreshToken(refreshToken, refreshTokenExpiry);
        this.tokenRepo.save(token);
    }

    public void cleanRefreshToken(Token token) {
        this.updateRefreshToken("", null, token);
    }
}
