package ute.mobile.back_end_for_BOOKING.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.common.domain.BaseEntity;
import ute.mobile.back_end_for_BOOKING.common.domain.BaseStatus;
import ute.mobile.back_end_for_BOOKING.models.dto.BookingStatus;

@Entity
@Table(name = "bookings")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE bookings SET is_deleted = true WHERE _id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking extends BaseEntity implements BaseStatus<BookingStatus> {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false)
  User customer;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id", nullable = false)
  Room room;

  @Column(nullable = false)
  LocalDate startDate;

  @Column(nullable = false)
  LocalDate endDate;

  @Column(nullable = false)
  Instant bookingTime;

  @Column(precision = 8, scale = 0, nullable = false)
  BigDecimal total;

  @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  List<BookedDate> bookedDates = new ArrayList<>();

  String status;
}