package com.omergundogdu.bilgeadamecommercebackend.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler, Spring Boot uygulaması için merkezi hata yönetimini sağlar.
 * Bu sınıf, uygulama genelindeki controller'larda oluşabilecek istisnaları yakalar ve
 * kullanıcıya anlamlı yanıtlar döner.
 *
 * <p>
 * Aşağıdaki istisna türlerini yakalar:
 * <ul>
 *     <li>{@link RuntimeException}</li>
 *     <li>{@link MethodArgumentNotValidException}</li>
 *     <li>{@link Exception} (genel hata yakalayıcı)</li>
 * </ul>
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * RuntimeException türündeki hataları yakalar.
     *
     * @param ex Fırlatılan RuntimeException
     * @return Hata mesajı içeren HTTP 400 (Bad Request) yanıtı
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * @Valid anotasyonu ile yapılan doğrulama hatalarını yakalar.
     * Alan bazlı hata mesajlarını döner.
     *
     * @param ex MethodArgumentNotValidException
     * @return Alanlara ait hata mesajlarını içeren HTTP 400 (Bad Request) yanıtı
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Tüm istisnaları genel olarak yakalayan metod.
     * Uygulama dışı beklenmeyen hataları loglamak ve yönetmek için kullanılır.
     *
     * @param ex Fırlatılan Exception
     * @return Genel hata mesajı içeren HTTP 500 (Internal Server Error) yanıtı
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAll(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", "Beklenmeyen bir hata oluştu: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
