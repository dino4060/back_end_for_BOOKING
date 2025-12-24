package ute.mobile.back_end_for_BOOKING.business;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.business.dto.TripBodyPatch;
import ute.mobile.back_end_for_BOOKING.business.dto.TripData;
import ute.mobile.back_end_for_BOOKING.business.mappers.TripMapper;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;
import ute.mobile.back_end_for_BOOKING.common.exception.NotFoundE;
import ute.mobile.back_end_for_BOOKING.models.Booking;
import ute.mobile.back_end_for_BOOKING.models.dto.BookingStatus;
import ute.mobile.back_end_for_BOOKING.models.repositories.BookingRepo;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TripService {

  BookingRepo bookingRepo;
  TripMapper mapper;

  public TripData patch(CurrentUser currentUser, TripBodyPatch body) {
    var tripId = body.getId();
    var booking = this.bookingRepo
        .findByIdAndCustomerId(tripId, currentUser.id())
        .orElseThrow(() -> new NotFoundE("Trip not found with id: " + tripId));

    if (body.getStatus() != null) {
      booking.setStatus(body.getStatus());
      if (booking.hasStatus(BookingStatus.CANCELED)) {
        booking.getBookedDates().clear();
      }
      this.bookingRepo.save(booking);
    }

    return this.mapper.toData(booking);
  }

  public List<TripData> list(CurrentUser currentUser) {
    var bookings = this.bookingRepo.findByCustomerId(currentUser.id());

    return bookings.stream()
        .sorted((small, large) -> large.getId().compareTo(small.getId()))
        .map(booking -> {
          this.updateBookingStatus(booking);
          return this.mapper.toData(booking);
        })
        .toList();
  }

  private void updateBookingStatus(Booking booking) {
    var today = LocalDate.now();

    // TH1: UPCOMING => check start date
    if (booking.hasStatus(BookingStatus.UPCOMING)) {
      if (!today.isBefore(booking.getStartDate())) {
        booking.setStatus(BookingStatus.ONGOING);
        this.bookingRepo.save(booking);
      }
    }
    // TH2: ONGOING => check end date
    else if (booking.hasStatus(BookingStatus.ONGOING)) {
      if (today.isAfter(booking.getEndDate())) {
        booking.setStatus(BookingStatus.ENDED);
        this.bookingRepo.save(booking);
      }
    }
  }
}