package ute.mobile.back_end_for_BOOKING.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ute.mobile.back_end_for_BOOKING.models.BookedDate;

public interface BookedDateRepo extends
    JpaRepository<BookedDate, Long>,
    JpaSpecificationExecutor<BookedDate> {
}