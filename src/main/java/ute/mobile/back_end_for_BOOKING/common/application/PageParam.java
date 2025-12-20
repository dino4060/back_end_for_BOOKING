package ute.mobile.back_end_for_BOOKING.common.application;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PageParam {
    // @Min(value = 1, message = "Minimum page is 1")
    private int page = 1;

    // @Min(value = 1, message = "Minimum size is 1")
    private int size = 10;

    // @Pattern(regexp = "id|price.mainPrice", message = "Sort must be id, price.mainPrice")
    private String sort = "id";

    // @Pattern(regexp = "ASC|DESC", message = "Direction should be ASC, DESC")
    private String direction = "DESC";
}
