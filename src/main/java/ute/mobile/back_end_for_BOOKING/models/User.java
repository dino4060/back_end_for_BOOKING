package ute.mobile.back_end_for_BOOKING.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.var;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.common.domain.BaseEntity;
import ute.mobile.back_end_for_BOOKING.models.dto.Role;

@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE user_id=?")
@SQLRestriction("is_deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    String name;

    @Column(nullable = false, unique = true)
    String username;

    @Column(unique = true)
    String email;

    @Column(unique = true)
    String phone;

    String password;

    Integer provinceId;

    Integer wardId;

    String street;

    Set<String> roles;

    // FACTORY //

    public User(Long id) {
        var user = new User();
        user.setId(id);
    }

    public static User createCustomer(String name, String email, String phone, String passHashed) {
        User user = new User();

        user.setName(name);
        user.setUsername("user" + System.currentTimeMillis());
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(passHashed);
        user.addRole(Role.CUSTOMER);

        return user;
    }

    public static User createThirdCustomer(String name, String email) {
        User user = new User();

        user.setName(name);
        user.setUsername("user" + System.currentTimeMillis());
        user.setEmail(email);
        user.addRole(Role.CUSTOMER);

        return user;
    }

    public static User createAdmin(String username, String email, String passHashed) {
        User user = new User();

        user.setName("Top 1 BOOKING BAR");
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passHashed);
        user.addRole(Role.ADMIN);

        return user;
    }

    // INSTANCE //

    public void updateCustomer(String name, String email, String phone) {
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
    }

    public void addRole(Role role) {
        if (this.getRoles() == null)
            this.setRoles(new HashSet<>());
        this.getRoles().add(role.name());
    }
}
