package ute.mobile.back_end_for_BOOKING.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.business.UserService;
import ute.mobile.back_end_for_BOOKING.business.dto.UserBody;
import ute.mobile.back_end_for_BOOKING.common.api.AuthUser;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

  UserService userService;

  @PutMapping
  public ResponseEntity<?> edit(
      @AuthUser CurrentUser currentUser,
      @RequestBody UserBody body) {

    var result = this.userService.edit(currentUser, body);
    return ResponseEntity.ok(result);
  }
}