package ute.mobile.back_end_for_BOOKING.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ute.mobile.back_end_for_BOOKING.api.dto.AuthRes;
import ute.mobile.back_end_for_BOOKING.business.AuthService;
import ute.mobile.back_end_for_BOOKING.business.dto.LoginEmailBody;
import ute.mobile.back_end_for_BOOKING.business.dto.LoginPhoneBody;
import ute.mobile.back_end_for_BOOKING.business.dto.RegisterBody;

// PublicAuthController //
@RestController
@RequestMapping("/api/public/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  // WRITE //

  // register //
  @PostMapping("/register")
  public ResponseEntity<AuthRes> register(
      @RequestBody RegisterBody body) {
    HttpHeaders headers = new HttpHeaders();
    AuthRes auth = this.authService.registerCustomer(body, headers);
    return ResponseEntity.ok().headers(headers).body(auth);
  }

  // login with phone //
  @PostMapping("/login/phone")
  public ResponseEntity<AuthRes> login(
      @RequestBody LoginPhoneBody body) {
    HttpHeaders headers = new HttpHeaders();
    AuthRes result = this.authService.login(body, headers);

    return ResponseEntity.ok().headers(headers).body(result);
  }

  @PostMapping("/login/email")
  public ResponseEntity<AuthRes> login(
      @RequestBody LoginEmailBody body) {
    HttpHeaders headers = new HttpHeaders();
    AuthRes result = this.authService.login(body, headers);

    return ResponseEntity.ok().headers(headers).body(result);
  }
}
