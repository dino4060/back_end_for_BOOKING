package ute.mobile.back_end_for_BOOKING.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ute.mobile.back_end_for_BOOKING.models.Room;

public interface RoomRepo extends
        JpaRepository<Room, Long>,
        JpaSpecificationExecutor<Room> {
}
