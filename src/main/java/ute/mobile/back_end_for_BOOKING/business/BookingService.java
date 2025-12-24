package ute.mobile.back_end_for_BOOKING.business;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.business.dto.BookingBody;
import ute.mobile.back_end_for_BOOKING.business.dto.BookingData;
import ute.mobile.back_end_for_BOOKING.business.mappers.BookingMapper;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;
import ute.mobile.back_end_for_BOOKING.common.exception.NotFoundE;
import ute.mobile.back_end_for_BOOKING.models.BookedDate;
import ute.mobile.back_end_for_BOOKING.models.Booking;
import ute.mobile.back_end_for_BOOKING.models.dto.BookingStatus;
import ute.mobile.back_end_for_BOOKING.models.repositories.BookedDateRepo;
import ute.mobile.back_end_for_BOOKING.models.repositories.BookingRepo;
import ute.mobile.back_end_for_BOOKING.models.repositories.RoomRepo;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class BookingService {

  BookingRepo bookingRepo;
  RoomRepo roomRepo;
  BookedDateRepo bookedDateRepo;
  BookingMapper mapper;

  public BookingData create(CurrentUser currentUser, BookingBody body) {
    // Find room
    var room = this.roomRepo.findById(body.getRoomId())
        .orElseThrow(() -> new NotFoundE("Room not found with id: " + body.getRoomId()));

    // Create booking
    var booking = new Booking();
    booking.setCustomer(currentUser.toUser());
    booking.setRoom(room);
    booking.setStartDate(body.getStartDate());
    booking.setEndDate(body.getEndDate());
    booking.setBookingTime(Instant.now());
    booking.setTotal(body.getTotal());
    booking.setStatus(BookingStatus.UPCOMING);

    var savedBooking = this.bookingRepo.save(booking);

    // Create booked dates
    var bookedDateValues = this.generateBookedDates(body.getStartDate(), body.getEndDate());
    var bookedDates = new ArrayList<BookedDate>();

    for (var date : bookedDateValues) {
      var bookedDate = new BookedDate();
      bookedDate.setRoom(room);
      bookedDate.setBooking(savedBooking);
      bookedDate.setDate(date);
      bookedDates.add(bookedDate);
    }

    this.bookedDateRepo.saveAll(bookedDates);

    return this.mapper.toData(savedBooking);
  }

  private List<LocalDate> generateBookedDates(LocalDate startDate, LocalDate endDate) {
    var dates = new ArrayList<LocalDate>();
    var current = startDate;

    while (!current.isAfter(endDate)) {
      dates.add(current);
      current = current.plusDays(1);
    }

    return dates;
  }
}