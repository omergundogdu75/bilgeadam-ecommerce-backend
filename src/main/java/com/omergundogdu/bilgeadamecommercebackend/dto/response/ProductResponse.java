package com.omergundogdu.bilgeadamecommercebackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ürün bilgilerini içeren response sınıfıdır.
 *
 * <p>Bu sınıf, ürünle ilgili tüm bilgileri içeren bir yanıt (response) nesnesidir.
 * Genellikle ürünlerin detaylarını dönerken kullanılır ve kullanıcıya veya uygulama katmanlarına
 * ürün bilgilerini iletmek için kullanılır.</p>
 *
 * <p><b>Alanlar:</b></p>
 * <ul>
 *   <li><b>id</b> - Ürünün benzersiz kimliği (primary key)</li>
 *   <li><b>name</b> - Ürünün adı</li>
 *   <li><b>description</b> - Ürünün açıklaması</li>
 *   <li><b>price</b> - Ürünün fiyatı</li>
 *   <li><b>stock</b> - Ürünün stok durumu</li>
 *   <li><b>imageUrl</b> - Ürünün görselinin URL'si</li>
 *   <li><b>category</b> - Ürünün ait olduğu kategori bilgisi</li>
 *   <li><b>brand</b> - Ürünün markası bilgisi</li>
 * </ul>
 *
 * <p><b>Kullanım Örneği (JSON):</b></p>
 * <pre>{@code
 * {
 *   "id": 1,
 *   "name": "Telefon",
 *   "description": "Akıllı telefon",
 *   "price": 999.99,
 *   "stock": 50,
 *   "imageUrl": "http://example.com/image.jpg",
 *   "category": {
 *     "id": 1,
 *     "name": "Elektronik"
 *   },
 *   "brand": {
 *     "id": 1,
 *     "name": "Samsung"
 *   }
 * }
 * }</pre>
 *
 * @author  Ömer Gündoğdu</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    /**
     * Ürünün benzersiz kimliği (primary key).
     */
    private Long id;

    /**
     * Ürünün adı.
     */
    private String name;

    /**
     * Ürünün açıklaması.
     */
    private String description;

    /**
     * Ürünün fiyatı.
     */
    private Double price;

    /**
     * Ürünün stok durumu.
     */
    private Integer stock;

    /**
     * Ürünün görselinin URL'si.
     */
    private String imageUrl;

    /**
     * Ürünün ait olduğu kategori bilgisi.
     */
    private CategoryResponse category;

    /**
     * Ürünün markası bilgisi.
     */
    private BrandResponse brand;
}
