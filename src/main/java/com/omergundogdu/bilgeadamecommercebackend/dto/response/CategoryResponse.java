package com.omergundogdu.bilgeadamecommercebackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Kategorilerin istemciye döndüğü response sınıfıdır.
 *
 * <p>Bu sınıf, bir kategoriyi temsil eder ve kategoriyle ilgili temel bilgilerin yanı sıra,
 * kategori altındaki alt kategorilerle ilgili bilgileri de içerir.</p>
 *
 * <p><b>Alanlar:</b></p>
 * <ul>
 *   <li><b>id</b> - Kategorinin benzersiz ID değeri</li>
 *   <li><b>name</b> - Kategorinin adı</li>
 *   <li><b>slug</b> - Kategoriye ait URL dostu ad (slug)</li>
 *   <li><b>imageUrl</b> - Kategoriye ait resmin URL'si</li>
 *   <li><b>children</b> - Kategorinin alt kategorilerini içeren liste</li>
 * </ul>
 *
 * <p><b>Kullanım Örneği (JSON):</b></p>
 * <pre>{@code
 * {
 *   "id": 1,
 *   "name": "Elektronik",
 *   "slug": "elektronik",
 *   "imageUrl": "https://example.com/image.jpg",
 *   "children": [
 *     {
 *       "id": 2,
 *       "name": "Bilgisayarlar",
 *       "slug": "bilgisayarlar",
 *       "imageUrl": "https://example.com/image2.jpg"
 *     },
 *     {
 *       "id": 3,
 *       "name": "Telefonlar",
 *       "slug": "telefonlar",
 *       "imageUrl": "https://example.com/image3.jpg"
 *     }
 *   ]
 * }
 * }</pre>
 *
 *  @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    /**
     * Kategorinin benzersiz kimliği (primary key).
     */
    private Long id;

    /**
     * Kategorinin adı.
     */
    private String name;

    /**
     * Kategoriye ait URL dostu ad (slug).
     */
    private String slug;

    /**
     * Kategoriye ait resmin URL'si.
     */
    private String imageUrl;

    /**
     * Kategorinin alt kategorilerini içeren liste.
     */
    private List<CategoryChildResponse> children;
}
