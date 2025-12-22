package ute.mobile.back_end_for_BOOKING.infrastructure.congatulation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.business.AuthService;
import ute.mobile.back_end_for_BOOKING.models.dto.Role;

import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Slf4j
public class AppInitConfig {
  @Bean
  ApplicationRunner applicationRunner(AuthService authService) {
    return args -> {
      log.info(">>> Initializing users...");

      // Host users
      authService.createExampleUser(
          "Ngọc Quyên",
          "0384445551",
          "123456",
          "",
          Set.of(Role.HOST.name()));

      authService.createExampleUser(
          "Trinh",
          "0384445552",
          "123456",
          "",
          Set.of(Role.HOST.name()));

      // Customer users
      authService.createExampleUser(
          "Xuân Thắng",
          "0385554441",
          "123456",
          "",
          Set.of(Role.CUSTOMER.name()));

      authService.createExampleUser(
          "Đình Lân",
          "0385554442",
          "123456",
          "",
          Set.of(Role.CUSTOMER.name()));

      log.info(">>> Users initialization completed");
    };
  }
}
