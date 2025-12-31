package ute.mobile.back_end_for_BOOKING.business.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import ute.mobile.back_end_for_BOOKING.business.dto.RoomParam;
import ute.mobile.back_end_for_BOOKING.common.application.PageSpec;
import ute.mobile.back_end_for_BOOKING.models.Room;

public class RoomSpec extends PageSpec {

  public static Specification<Room> toQueryable(RoomParam param) {
    return Specification
        .where(hasDestination(param.getDestination()))
        .and(inPriceRange(param.getMinPrice(), param.getMaxPrice()))
        .and(hasBedrooms(param.getBedrooms()))
        .and(hasBeds(param.getBeds()))
        .and(hasCoupleBed(param.getIsCoupleBed()))
        .and(hasBathrooms(param.getBathrooms()))
        .and(hasPrivateBathrooms(param.getIsPrivateBathrooms()))
        .and(isAvailable(param.getStartDate(), param.getEndDate()));
  }

  public static Specification<Room> isAvailable(LocalDate startDate, LocalDate endDate) {
    return (root, query, builder) -> {
      if (startDate == null || endDate == null)
        return null;

      LocalDate today = LocalDate.now();
      LocalDate effectiveStart = startDate.isBefore(today) ? today : startDate;

      if (endDate.isBefore(effectiveStart))
        return null;

      return builder.not(
          root.get("id").in(
              BookedDateSpec.subBookedRoomIds(query, builder, effectiveStart, endDate)));
    };
  }

  public static Specification<Room> hasBedrooms(Integer bedrooms) {
    return (root, query, builder) -> {
      if (bedrooms == null)
        return null;

      return builder.greaterThanOrEqualTo(
          root.get("bedRooms"),
          bedrooms);
    };
  }

  public static Specification<Room> hasBeds(Integer beds) {
    return (root, query, builder) -> {
      if (beds == null)
        return null;

      return builder.greaterThanOrEqualTo(
          root.get("beds"),
          beds);
    };
  }

  public static Specification<Room> hasCoupleBed(Boolean isCoupleBed) {
    return (root, query, builder) -> {
      if (isCoupleBed == null)
        return null;

      return builder.equal(
          root.get("isCoupleBed"),
          isCoupleBed);
    };
  }

  public static Specification<Room> hasBathrooms(Integer bathrooms) {
    return (root, query, builder) -> {
      if (bathrooms == null)
        return null;

      return builder.greaterThanOrEqualTo(
          root.get("bathRooms"),
          bathrooms);
    };
  }

  public static Specification<Room> hasPrivateBathrooms(Boolean isPrivateBathrooms) {
    return (root, query, builder) -> {
      if (isPrivateBathrooms == null)
        return null;

      return builder.equal(
          root.get("isPrivateBathrooms"),
          isPrivateBathrooms);
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

  public static Specification<Room> hasDestination(String destination) {
    return (root, query, builder) -> {
      if (destination == null || destination == "")
        return null;

      return builder.equal(
          root.get("destination"),
          destination);
    };
  }
}
