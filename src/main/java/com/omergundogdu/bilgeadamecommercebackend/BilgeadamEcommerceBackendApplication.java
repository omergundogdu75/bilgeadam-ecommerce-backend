package com.omergundogdu.bilgeadamecommercebackend;

import com.omergundogdu.bilgeadamecommercebackend.model.Role;
import com.omergundogdu.bilgeadamecommercebackend.model.User;
import com.omergundogdu.bilgeadamecommercebackend.repository.RoleRepository;
import com.omergundogdu.bilgeadamecommercebackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@SpringBootApplication
public class BilgeadamEcommerceBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilgeadamEcommerceBackendApplication.class, args);
    }

    @Component
    public class AdminInitializer implements CommandLineRunner {

        private final UserRepository userRepository;
        private final RoleRepository roleRepository;
        private final PasswordEncoder passwordEncoder;

        public AdminInitializer(UserRepository userRepository,
                                RoleRepository roleRepository,
                                PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        public void run(String... args) throws Exception {
            String adminEmail = "omergundogdu75@gmail.com";

            if (!userRepository.existsByEmail(adminEmail)) {
                Role adminRole = roleRepository.findByName("ADMIN")
                        .orElseGet(() -> {
                            Role role = new Role();
                            role.setName("ADMIN");
                            return roleRepository.save(role);
                        });

                User admin = new User();
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("123456")); // Güvenli bir parola kullan
                admin.setFullName("Ömer GÜNDOĞDU");
                admin.setRoles(Set.of(adminRole));

                userRepository.save(admin);

                System.out.println("✅ Admin kullanıcısı oluşturuldu: " + adminEmail);
            } else {
                System.out.println("ℹ️ Admin kullanıcısı zaten mevcut.");
            }
        }
    }


}
