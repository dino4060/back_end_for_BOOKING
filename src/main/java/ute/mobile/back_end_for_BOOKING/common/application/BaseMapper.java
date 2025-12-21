package ute.mobile.back_end_for_BOOKING.common.application;

import org.mapstruct.MappingTarget;

public interface BaseMapper<M, B, D> {

  void toModel(B body, @MappingTarget M model);

  D toData(M model);
}