package ute.mobile.back_end_for_BOOKING.common.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/public/hello")
    public String hello() {
        return "Hello Nhân Handsome 😍";
    }
}
