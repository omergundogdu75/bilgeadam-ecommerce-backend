package com.omergundogdu.bilgeadamecommercebackend.configurations;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Uygulama genelinde kullanılacak olan {@link ModelMapper} bean'inin yapılandırıldığı konfigürasyon sınıfıdır.
 *
 * <p>{@code ModelMapper}, DTO (Data Transfer Object) ile Entity sınıfları arasında otomatik dönüştürme işlemleri için kullanılır.</p>
 *
 * <p>Bu konfigürasyonda eşleşme stratejisi olarak {@code STRICT} kullanılmıştır,
 * bu sayede DTO ve Entity sınıflarındaki alan isimlerinin birebir eşleşmesi beklenir.</p>
 *
 * <p>Örnek kullanım:</p>
 * <pre>{@code
 *   modelMapper.map(userEntity, UserDto.class);
 * }</pre>
 *
 * @author Ömer Gündoğdu
 */
@Configuration
public class ModelMapperConfig {

    /**
     * {@code ModelMapper} bean'ini tanımlar ve eşleşme stratejisini {@code STRICT} olarak ayarlar.
     *
     * @return {@link ModelMapper} örneği
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
