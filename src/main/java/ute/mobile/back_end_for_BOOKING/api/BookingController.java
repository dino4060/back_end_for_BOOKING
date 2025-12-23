package ute.mobile.back_end_for_BOOKING.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.business.BookingService;
import ute.mobile.back_end_for_BOOKING.business.dto.BookingBody;
import ute.mobile.back_end_for_BOOKING.common.api.AuthUser;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingController {

  BookingService bookingService;

  @PostMapping
  public ResponseEntity<?> create(
      @AuthUser CurrentUser currentUser,
      @RequestBody BookingBody body) {

    var booking = this.bookingService.create(currentUser, body);
    return ResponseEntity.ok(booking);
  }
}