package com.omergundogdu.bilgeadamecommercebackend.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Uygulamanın çok dilli (i18n) yapılandırmasını sağlayan konfigürasyon sınıfıdır.
 * Locale bazlı mesaj dosyalarını yönetmek, dil değiştirme özelliğini etkinleştirmek
 * ve varsayılan dili belirlemek amacıyla kullanılır.
 *
 * <p>Kullanıcı URL'de `?lang=en` gibi bir parametre ile dil değiştirebilir.</p>
 *
 * Örnek: {@code http://localhost:8080/api/products?lang=en}
 *
 * Mesaj dosyaları: `src/main/resources/i18n/messages_tr.properties`, `messages_en.properties` vs.
 *
 * @author Ömer Gündoğdu
 */
@Configuration
public class LocaleConfig {

    /**
     * MessageSource Bean’i i18n mesaj dosyalarını `i18n/messages_*.properties` klasöründen okur.
     *
     * @return Mesaj kaynaklarını sağlayan {@link MessageSource}
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("i18n/messages");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    /**
     * LocaleResolver varsayılan dili belirler.
     * Burada varsayılan olarak Türkçe (`tr`) ayarlanmıştır.
     *
     * @return {@link LocaleResolver} örneği
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("tr"));
        return resolver;
    }

    /**
     * Kullanıcının dili değiştirebilmesini sağlayan interceptor.
     * URL parametresi olarak `?lang=en` gibi değerlerle çalışır.
     *
     * @return {@link LocaleChangeInterceptor} örneği
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // örnek: ?lang=en
        return interceptor;
    }

    /**
     * Spring MVC'ye interceptor'ı kaydeden yapılandırma.
     *
     * @param interceptor Dil değiştirme interceptor’ı
     * @return {@link WebMvcConfigurer} örneği
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer(LocaleChangeInterceptor interceptor) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(interceptor);
            }
        };
    }
}
