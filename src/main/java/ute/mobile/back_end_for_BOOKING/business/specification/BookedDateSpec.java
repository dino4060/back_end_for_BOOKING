package ute.mobile.back_end_for_BOOKING.business.specification;

import java.time.LocalDate;

import jakarta.persistence.criteria.CommonAbstractCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import ute.mobile.back_end_for_BOOKING.models.BookedDate;

public class BookedDateSpec {

  public static Subquery<Long> subBookedRoomIds(
      CommonAbstractCriteria query,
      CriteriaBuilder builder,
      LocalDate start,
      LocalDate end) {

    Subquery<Long> subquery = query.subquery(Long.class);
    Root<BookedDate> root = subquery.from(BookedDate.class);

    subquery
        .select(root.get("room").get("id"))
        .where(builder.between(root.get("date"), start, end));

    return subquery;
  }
}
