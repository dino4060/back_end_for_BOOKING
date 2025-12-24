package ute.mobile.back_end_for_BOOKING.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.business.TripService;
import ute.mobile.back_end_for_BOOKING.business.dto.TripBodyPatch;
import ute.mobile.back_end_for_BOOKING.common.api.AuthUser;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;

@RestController
@RequestMapping("/api/trips")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TripController {

  TripService tripService;

  @PatchMapping
  public ResponseEntity<?> patch(
      @AuthUser CurrentUser currentUser,
      @RequestBody TripBodyPatch body) {

    var result = this.tripService.patch(currentUser, body);
    return ResponseEntity.ok(result);
  }

  @GetMapping
  public ResponseEntity<?> list(
      @AuthUser CurrentUser currentUser) {

    var result = this.tripService.list(currentUser);
    return ResponseEntity.ok(result);
  }
}