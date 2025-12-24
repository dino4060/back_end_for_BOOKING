package ute.mobile.back_end_for_BOOKING.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ute.mobile.back_end_for_BOOKING.models.LikedRoom;

public interface LikedRoomRepo extends
    JpaRepository<LikedRoom, Long>,
    JpaSpecificationExecutor<LikedRoom> {

  Optional<LikedRoom> findByWishListIdAndRoomId(Long wishListId, Long roomId);
}