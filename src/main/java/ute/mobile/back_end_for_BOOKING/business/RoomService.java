package ute.mobile.back_end_for_BOOKING.business;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.api.dto.RoomData;
import ute.mobile.back_end_for_BOOKING.business.dto.RoomParam;
import ute.mobile.back_end_for_BOOKING.business.mappers.RoomMapper;
import ute.mobile.back_end_for_BOOKING.business.specification.RoomSpec;
import ute.mobile.back_end_for_BOOKING.common.application.PageData;
import ute.mobile.back_end_for_BOOKING.models.Room;
import ute.mobile.back_end_for_BOOKING.models.repositories.RoomRepo;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomService {

  RoomRepo repo;
  RoomMapper mapper;

  public PageData<RoomData> paginate(RoomParam param) {
    var page = this.repo.findAll(
        RoomSpec.toQueryable(param),
        RoomSpec.toPageable(param));

    return RoomSpec.toPageData(
        page,
        (Room room) -> this.mapper.toData(room));
  }

  public List<RoomData> list(RoomParam param) {
    var list = this.repo.findAll(
        RoomSpec.toQueryable(param));

    return list.stream()
        .map(room -> this.mapper.toData(room)).toList();
  }
}
