package ute.mobile.back_end_for_BOOKING.models;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.common.domain.BaseEntity;

@Entity
@Table(name = "wish_lists")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE wish_lists SET is_deleted = true WHERE _id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishList extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id", nullable = false, unique = true)
  User customer;

  @Column(nullable = false)
  Integer count = 0;

  @OneToMany(mappedBy = "wishList", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  List<LikedRoom> likedRooms = new ArrayList<>();
}