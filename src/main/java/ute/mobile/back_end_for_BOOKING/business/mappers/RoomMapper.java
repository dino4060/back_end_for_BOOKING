package ute.mobile.back_end_for_BOOKING.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ute.mobile.back_end_for_BOOKING.business.dto.RoomBody;
import ute.mobile.back_end_for_BOOKING.business.dto.RoomData;
import ute.mobile.back_end_for_BOOKING.common.application.BodyMapper;
import ute.mobile.back_end_for_BOOKING.common.application.DataMapper;
import ute.mobile.back_end_for_BOOKING.models.Room;

@Mapper(componentModel = "spring", uses = { BookedDateMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper extends BodyMapper<Room, RoomBody>, DataMapper<Room, RoomData> {
  // @Override
  // @Mapping(target = "bookedDates", source = "bookedDates", qualifiedByName =
  // "mapBookedDates")
  // RoomData toData(Room room);

  // @Named("mapBookedDates")
  // default List<LocalDate> mapBookedDates(List<BookedDate> bookedDates) {
  // if (bookedDates == null) {
  // return List.of();
  // }
  // return bookedDates.stream()
  // .map(bookedDate -> bookedDate.getDate())
  // .collect(Collectors.toList());
  // }
}
