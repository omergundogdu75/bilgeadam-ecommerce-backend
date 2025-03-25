package com.omergundogdu.bilgeadamecommercebackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Kategori alt elemanlarının (alt kategori) istemciye döndüğü response sınıfıdır.
 *
 * <p>Bu sınıf, kategorilerin altındaki alt kategorileri listelemek veya
 * görüntülemek için kullanılır. Genellikle ana kategori altında yer alan
 * alt kategorilerin bilgilerini içerir.</p>
 *
 * <p><b>Alanlar:</b></p>
 * <ul>
 *   <li><b>id</b> - Alt kategorinin benzersiz ID değeri</li>
 *   <li><b>name</b> - Alt kategorinin adı</li>
 *   <li><b>slug</b> - Alt kategoriye ait URL dostu ad (slug)</li>
 *   <li><b>imageUrl</b> - Alt kategoriye ait resmin URL'si (isteğe bağlı)</li>
 * </ul>
 *
 * <p><b>Kullanım Örneği (JSON):</b></p>
 * <pre>{@code
 * {
 *   "id": 1,
 *   "name": "Bilgisayarlar",
 *   "slug": "bilgisayarlar",
 *   "imageUrl": "https://example.com/image.jpg"
 * }
 * }</pre>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryChildResponse {

    /**
     * Alt kategorinin benzersiz kimliği (primary key).
     */
    private Long id;

    /**
     * Alt kategorinin adı.
     */
    private String name;

    /**
     * Alt kategoriye ait URL dostu ad (slug).
     */
    private String slug;

    /**
     * Alt kategoriye ait resmin URL'si.
     * (İsteğe bağlı bir alandır.)
     */
    private String imageUrl;
}
