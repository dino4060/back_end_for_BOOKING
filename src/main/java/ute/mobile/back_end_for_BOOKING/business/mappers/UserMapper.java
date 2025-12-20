package ute.mobile.back_end_for_BOOKING.business.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import ute.mobile.back_end_for_BOOKING.business.dto.UserBody;
import ute.mobile.back_end_for_BOOKING.common.application.PageMapper;
import ute.mobile.back_end_for_BOOKING.models.User;
import ute.mobile.back_end_for_BOOKING.ui.dto.UserData;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends PageMapper {

    void toModel(UserBody body, @MappingTarget User model);

    UserData toData(User user);
}
