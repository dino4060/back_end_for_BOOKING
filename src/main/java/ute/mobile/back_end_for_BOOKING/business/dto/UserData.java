package ute.mobile.back_end_for_BOOKING.business.dto;

import java.time.Instant;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.models.dto.Role;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserData {
  @JsonProperty("_id")
  Long _id;
  Long id;
  String name;
  String username;
  String email;
  String phone;
  String avatarUrl;
  Set<Role> roles;
  Instant createdAt;
  Instant updatedAt;
  Boolean isLogin;

  public void setId(Long id) {
    this.id = id;
    this._id = id;
    this.isLogin = true;
  }
}
