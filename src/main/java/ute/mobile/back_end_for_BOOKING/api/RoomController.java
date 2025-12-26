package ute.mobile.back_end_for_BOOKING.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.business.RoomService;
import ute.mobile.back_end_for_BOOKING.business.dto.RoomData;
import ute.mobile.back_end_for_BOOKING.business.dto.RoomParam;

@RestController
@RequestMapping("/api/public/rooms")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomController {

  RoomService roomService;

  @GetMapping
  public List<RoomData> list(
      @ModelAttribute RoomParam param) {

    System.out.println(">>> 102 " + param);
    return this.roomService.list(param);
  }

  @GetMapping("/{id}")
  public RoomData getById(@PathVariable Long id) {
    return this.roomService.getById(id);
  }
}
