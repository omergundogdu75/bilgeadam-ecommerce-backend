package com.omergundogdu.bilgeadamecommercebackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Üst kategori bilgilerini içeren response sınıfıdır.
 *
 * <p>Bu sınıf, bir kategorinin üst kategorisi ile ilgili bilgileri tutar.
 * Alt kategorilerde hangi üst kategoriye ait olduğunu belirtmek için kullanılabilir.</p>
 *
 * <p><b>Alanlar:</b></p>
 * <ul>
 *   <li><b>id</b> - Üst kategorinin benzersiz ID değeri</li>
 *   <li><b>name</b> - Üst kategorinin adı</li>
 * </ul>
 *
 * <p><b>Kullanım Örneği (JSON):</b></p>
 * <pre>{@code
 * {
 *   "id": 1,
 *   "name": "Elektronik"
 * }
 * }</pre>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentCategoryResponse {

    /**
     * Üst kategorinin benzersiz kimliği (primary key).
     */
    private Long id;

    /**
     * Üst kategorinin adı.
     */
    private String name;
}
