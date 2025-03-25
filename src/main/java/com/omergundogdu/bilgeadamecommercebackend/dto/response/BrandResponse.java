package com.omergundogdu.bilgeadamecommercebackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Marka (Brand) verilerinin istemciye döndüğü response sınıfıdır.
 *
 * <p>Bu sınıf, admin paneli veya kullanıcı arayüzü için marka bilgilerini
 * listeleme, görüntüleme gibi işlemlerde kullanılmak üzere tasarlanmıştır.</p>
 *
 * <p><b>Alanlar:</b></p>
 * <ul>
 *   <li><b>id</b> - Markanın benzersiz ID değeri</li>
 *   <li><b>name</b> - Markanın adı</li>
 *   <li><b>description</b> - Markaya ait açıklama</li>
 * </ul>
 *
 * <p><b>Kullanım Örneği (JSON):</b></p>
 * <pre>{@code
 * {
 *   "id": 1,
 *   "name": "Apple",
 *   "description": "Yüksek kaliteli teknoloji ürünleri"
 * }
 * }</pre>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {

    /**
     * Markanın benzersiz kimliği (primary key).
     */
    private Long id;

    /**
     * Markanın adı.
     */
    private String name;

    /**
     * Markaya ait açıklama metni.
     */
    private String description;
}
