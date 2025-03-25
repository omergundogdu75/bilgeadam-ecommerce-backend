package com.omergundogdu.bilgeadamecommercebackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT doğrulama ve kimlik doğrulama işlemini gerçekleştiren filtre sınıfı.
 * <p>
 * Bu filtre, gelen her HTTP isteğini kontrol eder ve eğer istek JWT token içeriyorsa,
 * token'ı doğrular ve kullanıcının kimliğini doğrulamak için token'dan kullanıcı bilgilerini çıkarır.
 * Ardından, güvenlik bağlamına (SecurityContext) kullanıcının kimliğini ekler.
 * </p>
 *
 * <p>
 * Filtre, yalnızca bir kez çalıştırılacak şekilde {@link OncePerRequestFilter} sınıfından türetilmiştir.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    /**
     * JWT işlemleri için kullanılan yardımcı sınıf.
     */
    private final JwtUtil jwtUtil;

    /**
     * Kullanıcı bilgilerini yüklemek için kullanılan servis.
     */
    private final UserDetailsService userDetailsService;

    /**
     * HTTP isteğini kontrol eder, eğer geçerli bir JWT token varsa, doğrular ve kimlik doğrulama işlemi yapar.
     * <p>
     * Eğer Authorization başlığı içeriyorsa ve token geçerli ise, kullanıcının kimliği doğrulanır
     * ve güvenlik bağlamına eklenir.
     * </p>
     *
     * @param request HTTP isteği
     * @param response HTTP cevabı
     * @param filterChain Filtre zinciri
     * @throws ServletException Servlet hatası
     * @throws IOException Giriş/Çıkış hatası
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Authorization başlığını al
        final String header = request.getHeader("Authorization");

        // Eğer header varsa ve Bearer token ile başlıyorsa
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);  // "Bearer " kısmını çıkart

            // Token süresi dolmamışsa
            if (!jwtUtil.isTokenExpired(token)) {
                // Token'dan kullanıcıyı çıkar
                String email = jwtUtil.extractUsername(token);

                // Kullanıcı bilgilerini yükle
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // Kullanıcıyı güvenlik bağlamına ekle
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Güvenlik bağlamını ayarla
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Bir sonraki filtreye geç
        filterChain.doFilter(request, response);
    }
}
