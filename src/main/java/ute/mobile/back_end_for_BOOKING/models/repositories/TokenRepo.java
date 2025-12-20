package ute.mobile.back_end_for_BOOKING.models.repositories;

import ute.mobile.back_end_for_BOOKING.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {
    Optional<Token> findByUserId(Long userId);
}
