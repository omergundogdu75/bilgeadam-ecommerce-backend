# Bilgeadam akademi bitirme projesi
# Backend
# GND E-Ticaret Uygulaması


Bu proje, Ömer Gündoğdu tarafından geliştirilen, Java Spring Boot backend ve Next.js frontend tabanlı bir modern e-ticaret uygulamasıdır.

---

## 🚀 Teknolojiler

### Backend (Spring Boot)
- Java 17+
- Spring Boot 3+
- Spring Security (JWT + Refresh Token)
- JPA + Hibernate
- PostgreSQL (veya alternatif veritabanları)
- Lombok, MapStruct
- Swagger
- E-mail

## 📂 Proje Klasör Yapısı

### Backend (`/backend`):
- `controller/` : Rest API endpointleri
- `service/` : İş mantığı katmanı
- `model/` : JPA entity'leri
- `dto/` : Request/response veri taşıyıcılar
- `repository/` : JPA repository interface'leri
- `security/` : JWT filter, token utils vs.

## 📆 Kurulum Adımları

### 1. Backend
```bash
./mvnw clean install
./mvnw spring-boot:run
```
> 🔎 Varsayılan port: `http://localhost:8080`
> 
> Bu proje, e-ticaret platformu için bir API sağlar. API'nin tüm uç noktalarını ve açıklamaları için [Swagger UI](http://localhost:8080/swagger-ui/index.html#/) sayfasını ziyaret edebilirsiniz.


## 🌐 Özellikler
- ✔️ Kullanıcı kayıt & giriş
- ✔️ Admin paneli (sipariş yönetimi, kategori/urun yönetimi)
- ✔️ JWT + Refresh token ile kimlik doğrulama
- ✔️ Sepet yönetimi (Context tabanlı)
- ✔️ Sipariş verme ve listesi
- ✔️ Toast mesajları ile bildirim

---

## 👥 Geliştirici
**Ömer Gündoğdu**
- [LinkedIn](https://linkedin.com/in/omergundogdu75)
- [GitHub](https://github.com/omergundogdu75)

---


Herhangi bir katkı, öneri veya sorun bildirimi için lütfen PR veya issue oluşturmaktan çekinmeyin. ✨

