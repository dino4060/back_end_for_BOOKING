package ute.mobile.back_end_for_BOOKING.common.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PageSpec {

  public static Pageable toPageable(PageParam param) {
    var pageNumber = param.getPage() - 1;
    var sizeNumber = param.getSize();
    var sort = Sort.by(
        param.getDirection().equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC,
        param.getSort());
    return PageRequest.of(pageNumber, sizeNumber, sort);
  }

  public static <M, D> PageData<D> toPageData(Page<M> page, Function<M, D> toDataFunc) {
    var totalPages = page.getTotalPages();
    var totalItems = (int) page.getTotalElements();
    var no = page.getNumber() + 1;
    var size = page.getSize();
    List<D> items = toDataFunc == null
        ? new ArrayList<>()
        : page.getContent().stream().map(model -> toDataFunc.apply(model)).toList();

    return new PageData<>(totalPages, totalItems, no, size, items);
  }
}
