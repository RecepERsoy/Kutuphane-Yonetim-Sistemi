import model.*;
import service.KutuphaneService;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static KutuphaneService service = new KutuphaneService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Kütüphane Yönetim Sistemi ---");
            System.out.println("1. Kitap Ekle");
            System.out.println("2. Kitap Listele");
            System.out.println("3. Kitap Sil");
            System.out.println("4. Kitap Güncelle");
            System.out.println("5. Stok Güncelle");
            System.out.println("6. Üye Ekle");
            System.out.println("7. Üye Listele");
            System.out.println("8. Üye Sil");
            System.out.println("9. Üye Güncelle");
            System.out.println("10. Kitap Ödünç Ver");
            System.out.println("11. Kitap İade Al");
            System.out.println("12. Ödünçleri Listele");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1 -> kitapEkleUI();
                case 2 -> kitapListeleUI();
                case 3 -> kitapSilUI();
                case 4 -> kitapGuncelleUI();
                case 5 -> stokGuncelleUI();
                case 6 -> uyeEkleUI();
                case 7 -> uyeListeleUI();
                case 8 -> uyeSilUI();
                case 9 -> uyeGuncelleUI();
                case 10 -> oduncVerUI();
                case 11 -> iadeAlUI();
                case 12 -> oduncleriListeleUI();
                case 0 -> {
                    service.tumunuKaydet();
                    System.out.println("Çıkılıyor...");
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    static void kitapEkleUI() {
        System.out.print("Kitap ID: ");
        String id = scanner.nextLine();
        System.out.print("Kitap Adı: ");
        String ad = scanner.nextLine();
        System.out.print("Yazar: ");
        String yazar = scanner.nextLine();
        System.out.print("Stok: ");
        int stok = scanner.nextInt(); scanner.nextLine();
        service.kitapEkle(new Kitap(id, ad, yazar, stok));
        System.out.println("Kitap eklendi.");
    }

    static void kitapListeleUI() {
        System.out.println("--- Kitaplar ---");
        for (Kitap k : service.kitaplariGetir()) System.out.println(k);
    }

    static void kitapSilUI() {
        System.out.print("Silinecek Kitap ID: ");
        String id = scanner.nextLine();
        service.kitapSil(id);
        System.out.println("İşlem tamamlandı.");
    }

    static void kitapGuncelleUI() {
        System.out.print("Güncellenecek Kitap ID: ");
        String id = scanner.nextLine();
        System.out.print("Yeni Ad: ");
        String ad = scanner.nextLine();
        System.out.print("Yeni Yazar: ");
        String yazar = scanner.nextLine();
        System.out.print("Yeni Stok: ");
        int stok = scanner.nextInt(); scanner.nextLine();
        
        if (service.kitapGuncelle(id, ad, yazar, stok)) {
            System.out.println("Kitap güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    static void stokGuncelleUI() {
        System.out.print("Stok güncellenecek Kitap ID: ");
        String id = scanner.nextLine();
        Kitap k = service.kitapBul(id);
        if (k != null) {
            System.out.println("Şu anki stok: " + k.stok);
            System.out.print("Yeni stok değeri: ");
            int yeniStok = scanner.nextInt(); scanner.nextLine();
            service.kitapGuncelle(id, k.ad, k.yazar, yeniStok);
            System.out.println("Stok başarıyla güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    static void uyeEkleUI() {
        System.out.print("Üye ID: ");
        String id = scanner.nextLine();
        System.out.print("Ad: ");
        String ad = scanner.nextLine();
        System.out.print("Soyad: ");
        String soyad = scanner.nextLine();
        service.uyeEkle(new Uye(id, ad, soyad));
        System.out.println("Üye eklendi.");
    }

    static void uyeListeleUI() {
        System.out.println("--- Üyeler ---");
        for (Uye u : service.uyeleriGetir()) System.out.println(u);
    }

    static void uyeSilUI() {
        System.out.print("Silinecek Üye ID: ");
        String id = scanner.nextLine();
        service.uyeSil(id);
        System.out.println("İşlem tamamlandı.");
    }

    static void uyeGuncelleUI() {
        System.out.print("Güncellenecek Üye ID: ");
        String id = scanner.nextLine();
        System.out.print("Yeni Ad: ");
        String ad = scanner.nextLine();
        System.out.print("Yeni Soyad: ");
        String soyad = scanner.nextLine();
        
        if (service.uyeGuncelle(id, ad, soyad)) {
            System.out.println("Üye güncellendi.");
        } else {
            System.out.println("Üye bulunamadı.");
        }
    }

    static void oduncVerUI() {
        System.out.print("Üye ID: ");
        String uyeId = scanner.nextLine();
        System.out.print("Kitap ID: ");
        String kitapId = scanner.nextLine();
        
        if (service.oduncVer(uyeId, kitapId)) {
            System.out.println("Ödünç verildi.");
        } else {
            System.out.println("İşlem başarısız. (Stok yok veya Kitap/Üye hatalı)");
        }
    }

    static void iadeAlUI() {
        System.out.print("Üye ID: ");
        String uyeId = scanner.nextLine();
        System.out.print("Kitap ID: ");
        String kitapId = scanner.nextLine();
        
        if (service.iadeAl(uyeId, kitapId)) {
            System.out.println("İade alındı.");
        } else {
            System.out.println("Kayıt bulunamadı.");
        }
    }

    static void oduncleriListeleUI() {
        System.out.println("--- Ödünç Kitaplar ---");
        for (Odunc o : service.oduncleriGetir()) System.out.println(o);
    }
}