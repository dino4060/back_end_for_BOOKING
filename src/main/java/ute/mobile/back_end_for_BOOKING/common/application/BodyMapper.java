package ute.mobile.back_end_for_BOOKING.common.application;

import org.mapstruct.MappingTarget;

public interface BodyMapper<M, B> {

  M toModel(B body);

  void toModel(B body, @MappingTarget M model);
}