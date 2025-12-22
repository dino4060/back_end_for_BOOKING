package ute.mobile.back_end_for_BOOKING.business;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.api.dto.UserData;
import ute.mobile.back_end_for_BOOKING.business.dto.UserBody;
import ute.mobile.back_end_for_BOOKING.business.dto.UserParam;
import ute.mobile.back_end_for_BOOKING.business.mappers.UserMapper;
import ute.mobile.back_end_for_BOOKING.business.specification.UserSpec;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;
import ute.mobile.back_end_for_BOOKING.common.application.PageData;
import ute.mobile.back_end_for_BOOKING.common.exception.DuplicationE;
import ute.mobile.back_end_for_BOOKING.common.exception.NotFoundE;
import ute.mobile.back_end_for_BOOKING.infrastructure.SecurityProvider;
import ute.mobile.back_end_for_BOOKING.models.User;
import ute.mobile.back_end_for_BOOKING.models.repositories.UserRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

  private final UserRepo userRepo;
  private final UserMapper userMapper;
  private final SecurityProvider securityProvider;

  public void checkEmailNotExists(String email) {
    userRepo.findByEmail(email)
        .ifPresent(user -> {
          throw new DuplicationE("Email already exists");
        });
  }

  public void checkPhoneNotExists(String phone) {
    userRepo.findByPhone(phone)
        .ifPresent(user -> {
          throw new DuplicationE("Phone already exists");
        });
  }

  public User createCustomer(String name, String email, String phone, String password) {
    String passHashed = this.securityProvider.hashPassword(password);
    User userToCreate = User.createCustomer(name, email, phone, passHashed);
    return this.userRepo.save(userToCreate);
  }

  public UserData edit(UserBody body, CurrentUser currentUser) {
    User user = this.userRepo
        .findById(currentUser.id())
        .orElseThrow(() -> new NotFoundE("User not found"));

    if (!body.getEmail().equals(user.getEmail())) {
      this.userRepo
          .findByEmail(body.getEmail())
          .ifPresent(existingUser -> {
            if (!existingUser.getId().equals(currentUser.id())) {
              throw new DuplicationE("Email already exists");
            }
          });
    }

    if (!body.getPhone().equals(user.getPhone())) {
      this.userRepo
          .findByPhone(body.getPhone())
          .ifPresent(existingUser -> {
            if (!existingUser.getId().equals(currentUser.id())) {
              throw new DuplicationE("Phone already exists.");
            }
          });
    }

    this.userMapper.toModel(body, user);
    User editUser = userRepo.save(user);
    return this.userMapper.toData(editUser);
  }

  public PageData<UserData> listCustomers(UserParam query) {
    var page = this.userRepo.findAll(
        UserSpec.toPageable(query));

    return UserSpec.toPageData(
        page,
        (User user) -> this.userMapper.toData(user));
  }
}
