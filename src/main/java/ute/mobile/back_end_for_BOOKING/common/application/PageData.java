package ute.mobile.back_end_for_BOOKING.common.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageData<T> {
  int totalPages;
  int totalItems;
  int page;
  int size;
  List<T> items = new ArrayList<>();
}
