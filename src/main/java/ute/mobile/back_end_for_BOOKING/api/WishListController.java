package ute.mobile.back_end_for_BOOKING.api;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.business.WishListService;
import ute.mobile.back_end_for_BOOKING.business.dto.LikedRoomParam;
import ute.mobile.back_end_for_BOOKING.common.api.AuthUser;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;

@RestController
@RequestMapping("/api/wish-lists")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WishListController {

  WishListService wishListService;

  @PostMapping("/rooms/{roomId}")
  public ResponseEntity<?> like(
      @AuthUser CurrentUser currentUser,
      @PathVariable Long roomId) {

    this.wishListService.like(currentUser, roomId);
    return ResponseEntity.ok(Map.of());
  }

  @DeleteMapping("/rooms/{roomId}")
  public ResponseEntity<?> unlike(
      @AuthUser CurrentUser currentUser,
      @PathVariable Long roomId) {

    this.wishListService.unlike(currentUser, roomId);
    return ResponseEntity.ok(Map.of());
  }

  @GetMapping("/rooms/{roomId}")
  public ResponseEntity<?> hasLike(
      @AuthUser CurrentUser currentUser,
      @PathVariable Long roomId) {

    var result = this.wishListService.hasLike(currentUser, roomId);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/rooms")
  public ResponseEntity<?> paginateOrList(
      @AuthUser CurrentUser currentUser,
      @ModelAttribute LikedRoomParam param,
      @RequestParam(name = "non-page", defaultValue = "false") boolean nonPage) {

    var result = nonPage
        ? this.wishListService.list(currentUser, param)
        : this.wishListService.paginate(currentUser, param);
    return ResponseEntity.ok(result);
  }
}