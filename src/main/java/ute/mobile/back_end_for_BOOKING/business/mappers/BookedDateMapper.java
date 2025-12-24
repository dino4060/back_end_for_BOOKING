package ute.mobile.back_end_for_BOOKING.business.mappers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import ute.mobile.back_end_for_BOOKING.models.BookedDate;

@Component
public class BookedDateMapper {
  public List<LocalDate> toLocalDates(List<BookedDate> bookedDates) {
    if (bookedDates == null) {
      return List.of();
    }
    return bookedDates.stream()
        .map(d -> d.getDate()).toList();
  }
}