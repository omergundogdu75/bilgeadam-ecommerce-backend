package com.omergundogdu.bilgeadamecommercebackend.service.impl;

import com.omergundogdu.bilgeadamecommercebackend.dto.request.BrandRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.BrandResponse;
import com.omergundogdu.bilgeadamecommercebackend.model.Brand;
import com.omergundogdu.bilgeadamecommercebackend.repository.BrandRepository;
import com.omergundogdu.bilgeadamecommercebackend.service.read.BrandReadService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.BrandWriteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Marka ile ilgili işlemleri gerçekleştiren servis sınıfı.
 * <p>
 * Bu sınıf, marka ekleme, güncelleme, silme ve listeleme işlemlerini gerçekleştirir.
 * Ayrıca, marka verilerini {@link BrandRequest} ve {@link BrandResponse} nesnelerine dönüştürür.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandReadService, BrandWriteService {

    /**
     * Marka veritabanı işlemleri için kullanılan repository.
     */
    private final BrandRepository brandRepository;

    /**
     * Model dönüşümleri için kullanılan ModelMapper sınıfı.
     */
    private final ModelMapper modelMapper;

    /**
     * Yeni bir marka oluşturur.
     * <p>
     * Eğer verilen isimde bir marka zaten varsa, hata fırlatılır.
     * Yeni marka veritabanına kaydedildikten sonra {@link BrandResponse} döndürülür.
     * </p>
     *
     * @param request Marka oluşturma isteği.
     * @return Oluşturulan marka bilgilerini içeren {@link BrandResponse} döner.
     */
    @Override
    public BrandResponse createBrand(BrandRequest request) {
        if (brandRepository.existsByNameIgnoreCase(request.getName())) {
            throw new RuntimeException("Bu marka zaten mevcut");
        }
        Brand brand = modelMapper.map(request, Brand.class);
        return modelMapper.map(brandRepository.save(brand), BrandResponse.class);
    }

    /**
     * Varolan bir markayı günceller.
     * <p>
     * Eğer marka bulunamazsa, hata fırlatılır. Marka adı ve açıklaması güncellenir
     * ve güncel marka bilgilerini içeren {@link BrandResponse} döndürülür.
     * </p>
     *
     * @param id Markanın benzersiz ID'si.
     * @param request Marka güncelleme isteği.
     * @return Güncellenmiş marka bilgilerini içeren {@link BrandResponse} döner.
     */
    @Override
    public BrandResponse updateBrand(Long id, BrandRequest request) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marka bulunamadı"));
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        return modelMapper.map(brandRepository.save(brand), BrandResponse.class);
    }

    /**
     * Verilen ID'ye sahip markayı siler.
     * <p>
     * Marka veritabanından silinir.
     * </p>
     *
     * @param id Silinecek markanın ID'si.
     */
    @Override
    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }

    /**
     * Verilen ID'ye sahip markayı getirir.
     * <p>
     * Eğer marka bulunamazsa, hata fırlatılır. Markanın bilgilerini içeren {@link BrandResponse} döndürülür.
     * </p>
     *
     * @param id Markanın benzersiz ID'si.
     * @return Markanın bilgilerini içeren {@link BrandResponse} döner.
     */
    @Override
    public BrandResponse getBrandById(Long id) {
        return brandRepository.findById(id)
                .map(b -> modelMapper.map(b, BrandResponse.class))
                .orElseThrow(() -> new RuntimeException("Marka bulunamadı"));
    }

    /**
     * Tüm markaları getirir.
     * <p>
     * Veritabanındaki tüm markalar {@link BrandResponse} formatına dönüştürülür
     * ve bir liste olarak döndürülür.
     * </p>
     *
     * @return Tüm markaların bilgilerini içeren {@link BrandResponse} listesini döner.
     */
    @Override
    public List<BrandResponse> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(b -> modelMapper.map(b, BrandResponse.class))
                .collect(Collectors.toList());
    }
}
