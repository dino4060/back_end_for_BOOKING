package ute.mobile.back_end_for_BOOKING.models.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ute.mobile.back_end_for_BOOKING.models.Booking;

public interface BookingRepo extends
    JpaRepository<Booking, Long>,
    JpaSpecificationExecutor<Booking> {

  List<Booking> findByCustomerId(Long customerId);

  Optional<Booking> findByIdAndCustomerId(Long id, Long customerId);
}