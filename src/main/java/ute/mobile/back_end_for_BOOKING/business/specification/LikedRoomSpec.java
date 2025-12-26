package ute.mobile.back_end_for_BOOKING.business.specification;

import org.springframework.data.jpa.domain.Specification;

import ute.mobile.back_end_for_BOOKING.business.dto.LikedRoomParam;
import ute.mobile.back_end_for_BOOKING.common.application.PageSpec;
import ute.mobile.back_end_for_BOOKING.models.LikedRoom;

public class LikedRoomSpec extends PageSpec {

  public static Specification<LikedRoom> toQueryable(LikedRoomParam param, Long wishListId) {
    return Specification
        .where(byWishListId(wishListId))
        .and(hasDestination(param.getDestination()));
  }

  public static Specification<LikedRoom> byWishListId(Long wishListId) {
    if (wishListId == null || wishListId <= 0)
      return null;
    return (root, query, builder) -> {
      var path = root.get("wishList").get("id");
      return builder.equal(path, wishListId);
    };
  }

  public static Specification<LikedRoom> hasDestination(String destination) {
    if (destination == null || destination == "")
      return null;
    return (root, query, builder) -> {
      var path = root.join("room").get("destination");
      return builder.equal(path, destination);
    };
  }
}