package ute.mobile.back_end_for_BOOKING.business.mappers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import ute.mobile.back_end_for_BOOKING.business.dto.RoomBody;
import ute.mobile.back_end_for_BOOKING.business.dto.RoomData;
import ute.mobile.back_end_for_BOOKING.common.application.DataMapper;
import ute.mobile.back_end_for_BOOKING.common.application.BodyMapper;
import ute.mobile.back_end_for_BOOKING.models.BookedDate;
import ute.mobile.back_end_for_BOOKING.models.Room;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper extends BodyMapper<Room, RoomBody>, DataMapper<Room, RoomData> {

  @Override
  @Mapping(target = "bookedDates", source = "bookedDates", qualifiedByName = "mapBookedDates")
  RoomData toData(Room room);

  @Named("mapBookedDates")
  default List<LocalDate> mapBookedDates(List<BookedDate> bookedDates) {
    if (bookedDates == null) {
      return List.of();
    }
    return bookedDates.stream()
        .map(BookedDate::getDate)
        .collect(Collectors.toList());
  }
}
