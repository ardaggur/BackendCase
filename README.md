# Trendyol Checkout Case
Bu case'de e-ticaret kullanan herkesin aşina olduğu sepet uygulamasının bir benzeri geliştirildi.

## Proje Açıklaması
Bu proje, Spring Boot, PostgreSQL, Unit Test, Postman ve DataGrip kullanılarak geliştirilen bir e-ticaret sitesi sepet uygulamasını içerir. Bu uygulama, müşterilerin alışveriş sepetlerine çeşitli ürünler eklemelerine, ürün çıkarmalarına,
sepetlerini boşaltmalarına, sepetlerini görüntülemeye, ürünlerine çeşitli servisler eklemelerine olanak tanır.

## Kurulum
Java ve Maven Kurulumu:
* Java'nın Resmi Web Sitesinden Java JDK'yi indirin ve kurun.
* Maven'in Resmi Web Sitesinden Maven'i indirin ve kurun.

PostgreSQL Veritabanı Kurulumu:
* PostgreSQL Resmi Web Sitesinden PostgreSQL veritabanını indirin ve kurun.
* Veritabanını oluşturun: create database e-commerce;
* Kullanıcı ve şifreyi oluşturun: create user ecommerce_user with encrypted password 'password';
* Veritabanına erişim izinlerini verin: grant all privileges on database ecommerce to ecommerce_user;

Projeyi İndirme ve Çalıştırma:
* Terminal veya Komut İstemcisinde projeyi indirin: git clone (https://github.com/ardaggur/BackendCase)
* Proje dizininize gidin.
* Uygulamayı çalıştırın: mvn spring-boot:run

Postman ile API Testi:
* Postman'i İndirin ve Kurun.
* Postman'de http://localhost:8080/cart ve http://localhost:8080/items endpointlerine POST , GET , DELETE request'lerini göndererek uygulamayı test edin.

## Kullanılan Teknolojiler
* Spring Boot: Java tabanlı bir framework. Uygulama temelini oluşturmak için kullanıldı.
* Maven : Pom.xml (Project Object Model) adlı yapı dosyası ile proje yapılandırması yapılır.
* Java 17 : Java dilindeki son sürüm olarak eklenen özellikler ve geliştirmelerle dolu bir sürümdür.
* PostgreSQL: Açık kaynaklı bir veritabanı yönetim sistemi. Müşteri ve ürün bilgilerini depolamak için kullanıldı.
* Unit Test: JUnit ve Mockito gibi araçlar kullanılarak uygulamanın birim testleri yazıldı.
* Postman: API endpoint'lerini test etmek ve doğrulamak için kullanıldı.
* DataGrip: Veritabanı yönetimi ve sorgu çalıştırma için kullanıldı.

## API Endpointleri 
* http://localhost:8080/items/addItem -> cart'a item ekleme işlemi
* http://localhost:8080/items/removeItem/{itemId} -> cart'tan item çıkartma işlemi
* http://localhost:8080/items/addVasItemToItem -> default item'a vas item ekleme işlemi
* http://localhost:8080/cart/resetCart -> cart'ı sıfırlama işlemi
* http://localhost:8080/cart/displayCart -> cart'ın özelliklerini gösterme işlemi

