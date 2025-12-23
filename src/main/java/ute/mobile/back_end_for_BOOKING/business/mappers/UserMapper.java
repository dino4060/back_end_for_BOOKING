package ute.mobile.back_end_for_BOOKING.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ute.mobile.back_end_for_BOOKING.business.dto.UserBody;
import ute.mobile.back_end_for_BOOKING.business.dto.UserData;
import ute.mobile.back_end_for_BOOKING.common.application.DataMapper;
import ute.mobile.back_end_for_BOOKING.common.application.BodyMapper;
import ute.mobile.back_end_for_BOOKING.models.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BodyMapper<User, UserBody>, DataMapper<User, UserData> {

}
