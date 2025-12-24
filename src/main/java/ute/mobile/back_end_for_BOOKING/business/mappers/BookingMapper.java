package ute.mobile.back_end_for_BOOKING.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ute.mobile.back_end_for_BOOKING.business.dto.BookingBody;
import ute.mobile.back_end_for_BOOKING.business.dto.BookingData;
import ute.mobile.back_end_for_BOOKING.common.application.BodyMapper;
import ute.mobile.back_end_for_BOOKING.common.application.DataMapper;
import ute.mobile.back_end_for_BOOKING.models.Booking;

@Mapper(componentModel = "spring", uses = { BookedDateMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper extends BodyMapper<Booking, BookingBody>, DataMapper<Booking, BookingData> {
}