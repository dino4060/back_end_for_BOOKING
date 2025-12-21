package ute.mobile.back_end_for_BOOKING.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ute.mobile.back_end_for_BOOKING.business.dto.RoomBody;
import ute.mobile.back_end_for_BOOKING.common.application.BaseMapper;
import ute.mobile.back_end_for_BOOKING.common.application.PageMapper;
import ute.mobile.back_end_for_BOOKING.models.Room;
import ute.mobile.back_end_for_BOOKING.ui.dto.RoomData;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomMapper extends BaseMapper<Room, RoomBody, RoomData>, PageMapper {
}
