package ute.mobile.back_end_for_BOOKING.business.specification;

import org.springframework.data.jpa.domain.Specification;

import ute.mobile.back_end_for_BOOKING.business.dto.WishListParam;
import ute.mobile.back_end_for_BOOKING.common.application.PageSpec;
import ute.mobile.back_end_for_BOOKING.models.LikedRoom;

public class WishListSpec extends PageSpec {

  public static Specification<LikedRoom> toQueryable(WishListParam param, Long wishListId) {
    return Specification.where(byWishListId(wishListId));
  }

  public static Specification<LikedRoom> byWishListId(Long wishListId) {
    return (root, query, builder) -> {
      if (wishListId == null)
        return null;
      return builder.equal(root.get("wishList").get("id"), wishListId);
    };
  }
}