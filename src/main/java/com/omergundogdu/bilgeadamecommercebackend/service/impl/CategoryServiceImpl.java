package com.omergundogdu.bilgeadamecommercebackend.service.impl;

import com.omergundogdu.bilgeadamecommercebackend.dto.response.CategoryChildResponse;
import com.omergundogdu.bilgeadamecommercebackend.dto.request.CategoryRequest;
import com.omergundogdu.bilgeadamecommercebackend.dto.response.CategoryResponse;
import com.omergundogdu.bilgeadamecommercebackend.model.Category;
import com.omergundogdu.bilgeadamecommercebackend.repository.CategoryRepository;
import com.omergundogdu.bilgeadamecommercebackend.service.read.CategoryReadService;
import com.omergundogdu.bilgeadamecommercebackend.service.write.CategoryWriteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Kategori ile ilgili işlemleri gerçekleştiren servis sınıfı.
 * <p>
 * Bu sınıf, kategori ekleme, güncelleme, silme, listeleme ve kategorinin alt kategorilerini yönetme işlemlerini gerçekleştirir.
 * Kategori adları için dinamik olarak slug üretme işlemi de yapılır.
 * </p>
 *
 * @author Ömer Gündoğdu
 */
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryReadService, CategoryWriteService {

    /**
     * Kategori veritabanı işlemleri için kullanılan repository.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Model dönüşümleri için kullanılan ModelMapper sınıfı.
     */
    private final ModelMapper modelMapper;

    /**
     * Yeni bir kategori oluşturur.
     * <p>
     * Kategori adı, slug, resim URL'si gibi bilgileri alır. Eğer `parentId` varsa, kategoriye bir üst kategori atanır.
     * Eğer `slug` boş veya null ise, kategori adına göre otomatik bir slug üretilir.
     * </p>
     *
     * @param request Kategori oluşturma isteği.
     * @return Oluşturulan kategorinin bilgilerini içeren {@link CategoryResponse} döner.
     */
    @Override
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());

        String slug = (request.getSlug() == null || request.getSlug().isBlank())
                ? generateSlug(request.getName())
                : request.getSlug();

        category.setSlug(slug);
        category.setImageUrl(request.getImageUrl());

        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        }

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    /**
     * Varolan bir kategoriyi günceller.
     * <p>
     * Kategori adı, slug ve resim URL'si güncellenir. Eğer `parentId` varsa, üst kategori atanır.
     * Eğer `slug` boş veya null ise, kategori adına göre otomatik bir slug üretilir.
     * </p>
     *
     * @param id Kategorinin benzersiz ID'si.
     * @param request Kategori güncelleme isteği.
     * @return Güncellenmiş kategorinin bilgilerini içeren {@link CategoryResponse} döner.
     */
    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(request.getName());
        // Eğer slug null ya da boşsa otomatik oluştur
        String slug = (request.getSlug() == null || request.getSlug().isBlank())
                ? generateSlug(request.getName())
                : request.getSlug();

        category.setSlug(slug);
        category.setImageUrl(request.getImageUrl());

        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

        return modelMapper.map(categoryRepository.save(category), CategoryResponse.class);
    }

    /**
     * Verilen ID'ye sahip kategoriyi siler.
     * <p>
     * Kategori veritabanından silinir.
     * </p>
     *
     * @param id Silinecek kategorinin ID'si.
     */
    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    /**
     * Verilen ID'ye sahip kategoriyi getirir.
     * <p>
     * Eğer kategori bulunamazsa, hata fırlatılır. Kategorinin bilgilerini içeren {@link CategoryResponse} döndürülür.
     * </p>
     *
     * @param id Kategorinin benzersiz ID'si.
     * @return Markanın bilgilerini içeren {@link CategoryResponse} döner.
     */
    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return modelMapper.map(category, CategoryResponse.class);
    }

    /**
     * Tüm ana kategorileri (parent'ı null olanları) getirir.
     * <p>
     * Veritabanındaki tüm ana kategoriler, alt kategorileriyle birlikte {@link CategoryResponse} formatına dönüştürülür
     * ve bir liste olarak döndürülür.
     * </p>
     *
     * @return Tüm ana kategorilerin bilgilerini içeren {@link CategoryResponse} listesini döner.
     */
    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getParent() == null)
                .map(this::convertToCategoryResponseWithChildren)
                .collect(Collectors.toList());
    }

    /**
     * Kategori adından slug oluşturur.
     * <p>
     * Slug, küçük harfe dönüştürülür, özel karakterler temizlenir ve boşluklar tireye çevrilir.
     * </p>
     *
     * @param name Kategori adı.
     * @return Oluşturulan slug.
     */
    private String generateSlug(String name) {
        return name.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "") // özel karakterleri temizle
                .replaceAll("\\s+", "-")       // boşlukları tire yap
                .trim();
    }

    /**
     * Bir kategori ve onun alt kategorilerini {@link CategoryResponse} formatında döndürür.
     *
     * @param category Kategori nesnesi.
     * @return Kategoriyi ve alt kategorilerini içeren {@link CategoryResponse}.
     */
    private CategoryResponse convertToCategoryResponseWithChildren(Category category) {
        List<CategoryChildResponse> children = category.getChildren().stream()
                .map(child -> new CategoryChildResponse(child.getId(), child.getName(), child.getSlug(), child.getImageUrl()))
                .collect(Collectors.toList());

        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .slug(category.getSlug())
                .imageUrl(category.getImageUrl())
                .children(children)
                .build();
    }
}
