package ute.mobile.back_end_for_BOOKING.business.specification;

import org.springframework.data.jpa.domain.Specification;

import ute.mobile.back_end_for_BOOKING.business.dto.RoomParam;
import ute.mobile.back_end_for_BOOKING.common.application.PageSpec;
import ute.mobile.back_end_for_BOOKING.models.Room;

public class RoomSpec extends PageSpec {

  public static Specification<Room> toQueryable(RoomParam param) {
    return Specification
        .where(hasDestination(param.getDestination()))
        .and(inPriceRange(param.getMinPrice(), param.getMaxPrice()));
  }

  public static Specification<Room> hasDestination(String destination) {

    return (root, query, builder) -> {
      if (destination == null || destination == "")
        return null;

      return builder.equal(
          root.get("destination"),
          destination);
    };
  }

  public static Specification<Room> inPriceRange(Integer minPrice, Integer maxPrice) {
    return (root, query, builder) -> {
      if (minPrice != null && maxPrice != null) {
        if (minPrice > maxPrice)
          return null;

        return builder.between(
            root.get("price"),
            minPrice,
            maxPrice);
      }

      if (minPrice != null) {
        return builder.greaterThanOrEqualTo(
            root.get("price"),
            minPrice);
      }

      if (maxPrice != null) {
        return builder.lessThanOrEqualTo(
            root.get("price"),
            maxPrice);
      }

      return null;
    };
  }
}
