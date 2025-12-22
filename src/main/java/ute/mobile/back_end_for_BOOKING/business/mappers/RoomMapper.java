package ute.mobile.back_end_for_BOOKING.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ute.mobile.back_end_for_BOOKING.api.dto.RoomData;
import ute.mobile.back_end_for_BOOKING.business.dto.RoomBody;
import ute.mobile.back_end_for_BOOKING.common.application.DataMapper;
import ute.mobile.back_end_for_BOOKING.common.application.BodyMapper;
import ute.mobile.back_end_for_BOOKING.models.Room;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper extends BodyMapper<Room, RoomBody>, DataMapper<Room, RoomData> {
}
