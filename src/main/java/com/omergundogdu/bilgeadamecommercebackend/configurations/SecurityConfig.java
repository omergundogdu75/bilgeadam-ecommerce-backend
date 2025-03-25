package com.omergundogdu.bilgeadamecommercebackend.configurations;

import com.omergundogdu.bilgeadamecommercebackend.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * Uygulamanın güvenlik ayarlarını yapılandırmak için kullanılan sınıftır.
 * JWT doğrulama, endpoint yetkilendirmeleri ve CORS politikaları burada tanımlanır.
 *
 * <p>Stateless mimaride çalıştığı için session yönetimi devre dışıdır.
 * Token doğrulama işlemleri {@link JwtFilter} üzerinden yapılır.</p>
 *
 * <p>Giriş yapılması gereken endpoint'ler `authenticated()` ile belirtilmiştir.
 * Diğer endpoint'ler `permitAll()` ile güvenlik dışı bırakılmıştır.</p>
 *
 * @author Ömer Gündoğdu
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    /**
     * Şifreleme için kullanılan {@link PasswordEncoder} bean tanımıdır.
     * <p>Şifreler BCrypt algoritmasıyla güvenli şekilde hashlenir.</p>
     *
     * @return BCryptPasswordEncoder örneği
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * {@link AuthenticationManager} bean'i oluşturur.
     * Giriş işlemlerinde kimlik doğrulama işlemleri için gereklidir.
     *
     * @param config Spring Authentication yapılandırması
     * @return AuthenticationManager örneği
     * @throws Exception yapılandırma hatası durumunda
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Uygulama için HTTP güvenlik yapılandırmasını içerir.
     * <ul>
     *     <li>CSRF kapatılır</li>
     *     <li>JWT tabanlı kimlik doğrulama uygulanır</li>
     *     <li>Bazı endpoint'ler herkese açık bırakılır</li>
     *     <li>JWT filtre zincire eklenir</li>
     * </ul>
     *
     * @param http HttpSecurity yapılandırıcısı
     * @return SecurityFilterChain örneği
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF koruması devre dışı (stateless API için uygundur)
                .cors(Customizer.withDefaults()) // CORS ayarlarını uygular
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Session tutulmaz
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",          // Giriş / kayıt endpointleri
                                "/api/categories",       // Kategori listele
                                "/api/categories/**",
                                "/api/products",         // Ürün listele
                                "/api/products/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/orders").authenticated()   // Sipariş oluşturma: giriş gerekli
                        .requestMatchers(HttpMethod.GET, "/api/orders/**").authenticated() // Sipariş görüntüleme: giriş gerekli
                        .anyRequest().permitAll() // Diğer tüm istekler serbest
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // JWT filtreyi zincire ekle

        return http.build();
    }

    /**
     * CORS politikalarını yapılandırır.
     * <p>Frontend (örneğin: http://localhost:3000) isteklerinin kabul edilmesini sağlar.</p>
     *
     * @return CorsConfigurationSource örneği
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000")); // Frontend adresi
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*")); // Tüm header'lara izin ver
        config.setAllowCredentials(true);       // Kimlik bilgileri ile istek yapılabilir

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Tüm endpoint'ler için geçerli

        return source;
    }
}
