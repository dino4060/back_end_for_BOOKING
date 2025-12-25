package ute.mobile.back_end_for_BOOKING.business;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.business.dto.LikedRoomData;
import ute.mobile.back_end_for_BOOKING.business.dto.LikedRoomParam;
import ute.mobile.back_end_for_BOOKING.business.mappers.LikedRoomMapper;
import ute.mobile.back_end_for_BOOKING.business.specification.LikedRoomSpec;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;
import ute.mobile.back_end_for_BOOKING.common.application.PageData;
import ute.mobile.back_end_for_BOOKING.common.exception.NotFoundE;
import ute.mobile.back_end_for_BOOKING.models.LikedRoom;
import ute.mobile.back_end_for_BOOKING.models.WishList;
import ute.mobile.back_end_for_BOOKING.models.repositories.LikedRoomRepo;
import ute.mobile.back_end_for_BOOKING.models.repositories.RoomRepo;
import ute.mobile.back_end_for_BOOKING.models.repositories.WishListRepo;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class WishListService {

  WishListRepo wishListRepo;
  LikedRoomRepo likedRoomRepo;
  RoomRepo roomRepo;
  LikedRoomMapper likedRoomMapper;

  public void like(CurrentUser currentUser, Long roomId) {
    var wishList = this.getOrCreateWishList(currentUser);

    var existingLikedRoom = this.likedRoomRepo.findByWishListIdAndRoomId(wishList.getId(), roomId);

    if (existingLikedRoom.isPresent()) {
      return;
    }

    var room = this.roomRepo.findById(roomId)
        .orElseThrow(() -> new NotFoundE("Room not found with id: " + roomId));

    var likedRoom = new LikedRoom();
    likedRoom.setWishList(wishList);
    likedRoom.setRoom(room);
    this.likedRoomRepo.save(likedRoom);

    wishList.setCount(wishList.getCount() + 1);
    this.wishListRepo.save(wishList);
  }

  public void unlike(CurrentUser currentUser, Long roomId) {
    var wishList = this.getOrCreateWishList(currentUser);

    var existingLikedRoom = this.likedRoomRepo.findByWishListIdAndRoomId(wishList.getId(), roomId);

    if (existingLikedRoom.isEmpty()) {
      return;
    }

    this.likedRoomRepo.delete(existingLikedRoom.get());

    wishList.setCount(Math.max(0, wishList.getCount() - 1));
    this.wishListRepo.save(wishList);
  }

  public boolean hasLike(CurrentUser currentUser, Long roomId) {
    var wishList = this.getOrCreateWishList(currentUser);

    var isLikedRoom = this.likedRoomRepo
        .findByWishListIdAndRoomId(wishList.getId(), roomId)
        .map((non) -> true)
        .orElseGet(() -> false);

    return isLikedRoom;
  }

  public PageData<LikedRoomData> paginate(CurrentUser currentUser, LikedRoomParam param) {
    var wishList = this.getOrCreateWishList(currentUser);

    var page = this.likedRoomRepo.findAll(
        LikedRoomSpec.toQueryable(param, wishList.getId()),
        LikedRoomSpec.toPageable(param));

    return LikedRoomSpec.toPageData(
        page,
        (LikedRoom likedRoom) -> this.likedRoomMapper.toData(likedRoom));
  }

  public List<LikedRoomData> list(CurrentUser currentUser, LikedRoomParam param) {
    var wishList = this.getOrCreateWishList(currentUser);

    var list = this.likedRoomRepo.findAll(
        LikedRoomSpec.toQueryable(param, wishList.getId()));

    return list.stream()
        .map((likedRoom) -> this.likedRoomMapper.toData(likedRoom)).toList();
  }

  private WishList getOrCreateWishList(CurrentUser currentUser) {
    return this.wishListRepo.findByCustomerId(currentUser.id())
        .orElseGet(() -> {
          var wishList = new WishList();
          wishList.setCustomer(currentUser.toUser());
          wishList.setCount(0);
          return this.wishListRepo.save(wishList);
        });
  }
}