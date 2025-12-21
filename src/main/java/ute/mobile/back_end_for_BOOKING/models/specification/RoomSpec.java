package ute.mobile.back_end_for_BOOKING.models.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import ute.mobile.back_end_for_BOOKING.business.dto.RoomParam;
import ute.mobile.back_end_for_BOOKING.models.Room;

@Component
public class RoomSpec {

    public Specification<Room> toQueryable(RoomParam param) {
        return Specification.where(
                likeFullText(param.getKeywords()));
    }

    public static Specification<Room> likeFullText(String text) {
        return (root, query, builder) -> {
            if (text == null || text.trim().isEmpty())
                return null;
            String pattern = "%" + text.toLowerCase() + "%";

            return builder.or(
                    builder.like(builder.lower(root.get("highlight")), pattern),
                    builder.like(builder.lower(root.get("detail")), pattern));
        };
    }
}
