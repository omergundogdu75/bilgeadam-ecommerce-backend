package com.omergundogdu.bilgeadamecommercebackend.dto.request;

import lombok.Data;

/**
 * Kategori oluşturma veya güncelleme işlemleri için kullanılan veri transfer nesnesidir (DTO).
 *
 * <p>
 * İstemciden gelen kategori bilgilerini (isim, slug, üst kategori ID'si ve görsel URL) taşır.
 * </p>
 *
 * <p><b>Kullanım:</b> /api/categories endpoint'leriyle birlikte POST ve PUT işlemlerinde kullanılır.</p>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
public class CategoryRequest {

    /**
     * Kategorinin görünen adı.
     * Örnek: "Elektronik", "Spor", "Giyim"
     */
    private String name;

    /**
     * URL dostu, benzersiz kısa ad (slug).
     * Örnek: "elektronik", "spor", "giyim"
     */
    private String slug;

    /**
     * Üst kategori ID'si. Ana kategori ise null olabilir.
     * Örnek: 1, 2, null
     */
    private Long parentId;

    /**
     * Kategoriye ait görselin URL adresi.
     * Örnek: "https://cdn.site.com/images/kategori.png"
     */
    private String imageUrl;
}
