package ute.mobile.back_end_for_BOOKING.common.application;

public interface DataMapper<M, D> {

  D toData(M model);
}