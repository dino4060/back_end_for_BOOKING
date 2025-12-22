package ute.mobile.back_end_for_BOOKING.business;

import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.api.dto.AuthRes;
import ute.mobile.back_end_for_BOOKING.business.dto.RegisterBody;
import ute.mobile.back_end_for_BOOKING.business.patterns.AuthFacade;
import ute.mobile.back_end_for_BOOKING.business.patterns.AuthTemplateImpl;
import ute.mobile.back_end_for_BOOKING.infrastructure.SecurityProvider;
import ute.mobile.back_end_for_BOOKING.models.Token;
import ute.mobile.back_end_for_BOOKING.models.User;
import ute.mobile.back_end_for_BOOKING.models.dto.Role;
import ute.mobile.back_end_for_BOOKING.models.repositories.TokenRepo;
import ute.mobile.back_end_for_BOOKING.models.repositories.UserRepo;

@Service
@Slf4j
public class AuthService extends AuthTemplateImpl {

  private final AuthFacade authFacade;

  private final UserService userService;

  private final TokenService tokenService;

  private final SecurityProvider securityProvider;

  private final UserRepo userRepo;

  private final TokenRepo tokenRepo;

  public AuthService(
      AuthFacade authFacade, UserService userService, TokenService tokenService, SecurityProvider securityProvider,
      UserRepo userRepo, TokenRepo tokenRepo) {
    super(authFacade);
    this.authFacade = authFacade;
    this.userService = userService;
    this.tokenService = tokenService;
    this.securityProvider = securityProvider;
    this.userRepo = userRepo;
    this.tokenRepo = tokenRepo;
  }

  protected Role getRole() {
    return Role.CUSTOMER;
  }

  public AuthRes registerCustomer(RegisterBody body, HttpHeaders headers) {
    this.userService.checkEmailNotExists(body.email());
    this.userService.checkPhoneNotExists(body.phone());

    User user = this.userService.createCustomer(
        body.name(), body.email(), body.phone(), body.password());
    this.tokenService.createToken(user);

    return this.authFacade.inAuth(user, headers);
  }

  public void createExampleUser(String name, String phone, String password, String avatarUrl, Set<String> roles) {
    var existingUser = this.userRepo.findByPhone(phone);

    if (existingUser.isPresent()) {
      return;
    }

    var user = new User();
    user.setName(name);
    user.setPhone(phone);
    user.setUsername(phone);
    user.setPassword(this.securityProvider.hashPassword(password));
    user.setAvatarUrl(avatarUrl);
    user.setRoles(roles);

    var savedUser = this.userRepo.save(user);
    log.info(">>> Created user: {} with phone: {}", name, phone);

    // Create token for user
    var token = new Token();
    token.setUser(savedUser);

    this.tokenRepo.save(token);
  }
}
