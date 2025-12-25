package ute.mobile.back_end_for_BOOKING.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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

@Entity
@Table(name = "rooms")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE rooms SET is_deleted = true WHERE room_id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room extends BaseEntity {

  String name;

  String highlight;

  @Column(columnDefinition = "TEXT")
  String detail;

  String transit;

  @Column(columnDefinition = "TEXT")
  String houseRules;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "host_id")
  User host;

  String street;

  String country;

  String smartLocation;

  @Column(precision = 18, scale = 15)
  BigDecimal latitude;

  @Column(precision = 18, scale = 15)
  BigDecimal longitude;

  String roomType;

  Integer bathRooms;

  Boolean isPrivateBathrooms;

  Integer bedRooms;

  Integer beds;

  Boolean isCoupleBed;

  @Column(precision = 10, scale = 2)
  BigDecimal price;

  @Column(precision = 10, scale = 2)
  BigDecimal weeklyPrice;

  Float reviewStars;

  @ElementCollection
  @CollectionTable(name = "room_amenities", joinColumns = @JoinColumn(name = "room_id"))
  @Column(name = "amenity")
  List<String> amenities = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "room_thumbnail_urls", joinColumns = @JoinColumn(name = "room_id"))
  @Column(name = "thumbnail_url")
  List<String> thumbnailUrls = new ArrayList<>();

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  List<BookedDate> bookedDates = new ArrayList<>();

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  List<LikedRoom> likedRooms = new ArrayList<>();
}