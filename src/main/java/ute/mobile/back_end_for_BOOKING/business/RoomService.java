package ute.mobile.back_end_for_BOOKING.business;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.business.dto.RoomParam;
import ute.mobile.back_end_for_BOOKING.business.mappers.RoomMapper;
import ute.mobile.back_end_for_BOOKING.common.application.PageData;
import ute.mobile.back_end_for_BOOKING.models.Room;
import ute.mobile.back_end_for_BOOKING.models.repositories.RoomRepo;
import ute.mobile.back_end_for_BOOKING.models.specification.RoomSpec;
import ute.mobile.back_end_for_BOOKING.ui.dto.RoomData;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoomService {

    RoomRepo repo;
    RoomMapper mapper;
    RoomSpec spec;

    public PageData<RoomData> paginate(RoomParam query) {
        var page = this.repo.findAll(
                this.spec.toQueryable(query),
                this.mapper.toPageable(query));

        return this.mapper.toPageData(
                page,
                (Room room) -> this.mapper.toData(room));
    }

    public List<RoomData> list(RoomParam query) {
        var list = this.repo.findAll(
                this.spec.toQueryable(query));

        return list.stream()
                .map(room -> this.mapper.toData(room)).toList();
    }
}
