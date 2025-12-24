package ute.mobile.back_end_for_BOOKING.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ute.mobile.back_end_for_BOOKING.models.WishList;

public interface WishListRepo extends
    JpaRepository<WishList, Long>,
    JpaSpecificationExecutor<WishList> {

  Optional<WishList> findByCustomerId(Long customerId);
}