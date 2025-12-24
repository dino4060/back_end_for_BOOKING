package ute.mobile.back_end_for_BOOKING.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ute.mobile.back_end_for_BOOKING.business.dto.TripData;
import ute.mobile.back_end_for_BOOKING.common.application.DataMapper;
import ute.mobile.back_end_for_BOOKING.models.Booking;

@Mapper(componentModel = "spring", uses = { BookedDateMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripMapper extends DataMapper<Booking, TripData> {
}