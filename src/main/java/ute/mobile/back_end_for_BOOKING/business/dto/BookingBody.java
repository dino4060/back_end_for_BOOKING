package ute.mobile.back_end_for_BOOKING.business.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingBody {
  Long roomId;
  LocalDate startDate;
  LocalDate endDate;
  BigDecimal total;
}