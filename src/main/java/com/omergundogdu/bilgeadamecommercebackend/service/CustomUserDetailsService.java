package com.omergundogdu.bilgeadamecommercebackend.service;

import com.omergundogdu.bilgeadamecommercebackend.model.User;
import com.omergundogdu.bilgeadamecommercebackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Kullanıcı detaylarını yüklemek için kullanılan özel UserDetailsService implementasyonu.
 * <p>
 * Bu sınıf, kullanıcının bilgilerini {@link UserRepository} aracılığıyla yükler ve
 * Spring Security için gerekli olan {@link UserDetails} nesnesini oluşturur.
 * Ayrıca, kullanıcının rollerine ve izinlerine göre {@link GrantedAuthority} nesneleri oluşturur.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Kullanıcı veritabanı işlemleri için kullanılan repository.
     */
    private final UserRepository userRepository;

    /**
     * Kullanıcı adı (e-posta) ile kullanıcıyı yükler.
     * <p>
     * Eğer kullanıcı veritabanında bulunamazsa, {@link UsernameNotFoundException} fırlatılır.
     * Kullanıcıya ait tüm izinler {@link GrantedAuthority} nesnelerine dönüştürülür.
     * </p>
     *
     * @param email Kullanıcı e-posta adresi.
     * @return Kullanıcı detaylarını içeren {@link UserDetails} nesnesi.
     * @throws UsernameNotFoundException Eğer kullanıcı bulunamazsa.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Kullanıcıyı veritabanından al
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + email));

        // Kullanıcının izinlerini al ve Authorities (yetkiler) listesine dönüştür
        Set<GrantedAuthority> authorities = user.getAllPermissions().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // Kullanıcıyı ve yetkilerini içeren UserDetails nesnesi döndür
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
