package ute.mobile.back_end_for_BOOKING.models;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.common.domain.BaseEntity;

@Entity
@Table(name = "room_booked_dates")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE room_booked_dates SET is_deleted = true WHERE _id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookedDate extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "room_id")
  Room room;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "booking_id")
  Booking booking;

  @Column(name = "booked_date")
  LocalDate date;
}