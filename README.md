Kütüphane Yönetim Sistemi (Console Application)

Bu proje, temel kütüphane işlemlerinin (kitap/üye yönetimi ve ödünç verme) yapıldığı, Java ile geliştirilmiş bir konsol uygulamasıdır. Proje, kodun sürdürülebilirliğini ve okunabilirliğini artırmak amacıyla **Katmanlı Mimari (Layered Architecture)** prensiplerine uygun olarak tasarlanmıştır.

 Mimari Yapı

Proje 4 ana katmandan oluşmaktadır:
* **Model:** Veri yapılarını (`Kitap`, `Uye`, `Odunc`) temsil eden nesneler.
* **Repository:** Verilerin `.txt` dosyalarına yazılması ve okunmasından sorumlu veri erişim katmanı.
* **Service:** İş kurallarının (stok kontrolü, ödünç verme mantığı vb.) işletildiği ana katman.
* **UI:** Kullanıcı ile etkileşime geçilen ve menülerin gösterildiği sunum katmanı (`Main.java`).

##  Özellikler

* Yeni kitap ve üye ekleme / silme / güncelleme
* Stok takibi
* Kitap ödünç verme ve iade alma işlemleri
* Tüm verilerin `.txt` formatında kalıcı olarak saklanması

##  Nasıl Çalıştırılır?

Projeyi terminal üzerinden derlemek ve çalıştırmak için `src` dizini altındayken aşağıdaki komutları sırasıyla çalıştırın:

1. **Derleme:**
   javac model/*.java repository/*.java service/*.java Main.java
   
2.**Çalıştırma:**
  java Main
