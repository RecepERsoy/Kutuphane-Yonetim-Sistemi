import java.io.*;
import java.util.*;

public class KutuphaneYonetimSistemi {
    static Scanner scanner = new Scanner(System.in);
    static List<Kitap> kitaplar = new ArrayList<>();
    static List<Uye> uyeler = new ArrayList<>();
    static List<Odunc> oduncler = new ArrayList<>();

    public static void main(String[] args) {
        kitaplariYukle();
        uyeleriYukle();
        oduncleriYukle();

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
                case 1 -> kitapEkle();
                case 2 -> kitapListele();
                case 3 -> kitapSil();
                case 4 -> kitapGuncelle();
                case 5 -> stokGuncelle();
                case 6 -> uyeEkle();
                case 7 -> uyeListele();
                case 8 -> uyeSil();
                case 9 -> uyeGuncelle();
                case 10 -> oduncVer();
                case 11 -> iadeAl();
                case 12 -> oduncleriListele();
                case 0 -> {
                    kitaplariKaydet();
                    uyeleriKaydet();
                    oduncleriKaydet();
                    System.out.println("Çıkılıyor...");
                    return;
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    static class Kitap {
        String id, ad, yazar;
        int stok;
        public Kitap(String id, String ad, String yazar, int stok) {
            this.id = id; this.ad = ad; this.yazar = yazar; this.stok = stok;
        }
        public String toString() {
            return id + " - " + ad + " - " + yazar + " - Stok: " + stok;
        }
    }

    static class Uye {
        String id, ad, soyad;
        public Uye(String id, String ad, String soyad) {
            this.id = id; this.ad = ad; this.soyad = soyad;
        }
        public String toString() {
            return id + " - " + ad + " " + soyad;
        }
    }

    static class Odunc {
        String uyeId, kitapId, tarih;
        public Odunc(String uyeId, String kitapId, String tarih) {
            this.uyeId = uyeId; this.kitapId = kitapId; this.tarih = tarih;
        }
        public String toString() {
            return "Üye: " + uyeId + ", Kitap: " + kitapId + ", Tarih: " + tarih;
        }
    }

    static void kitapEkle() {
        System.out.print("Kitap ID: ");
        String id = scanner.nextLine();
        System.out.print("Kitap Adı: ");
        String ad = scanner.nextLine();
        System.out.print("Yazar: ");
        String yazar = scanner.nextLine();
        System.out.print("Stok: ");
        int stok = scanner.nextInt(); scanner.nextLine();
        kitaplar.add(new Kitap(id, ad, yazar, stok));
        kitaplariKaydet();
        System.out.println("Kitap eklendi.");
    }

    static void kitapListele() {
        System.out.println("--- Kitaplar ---");
        for (Kitap k : kitaplar) System.out.println(k);
    }

    static void kitapSil() {
        System.out.print("Silinecek Kitap ID: ");
        String id = scanner.nextLine();
        kitaplar.removeIf(k -> k.id.equals(id));
        kitaplariKaydet();
        System.out.println("Kitap silindi (varsa).");
    }

    static void kitapGuncelle() {
        System.out.print("Güncellenecek Kitap ID: ");
        String id = scanner.nextLine();
        for (Kitap k : kitaplar) {
            if (k.id.equals(id)) {
                System.out.print("Yeni Ad: ");
                k.ad = scanner.nextLine();
                System.out.print("Yeni Yazar: ");
                k.yazar = scanner.nextLine();
                System.out.print("Yeni Stok: ");
                k.stok = scanner.nextInt(); scanner.nextLine();
                kitaplariKaydet();
                System.out.println("Kitap güncellendi.");
                return;
            }
        }
        System.out.println("Kitap bulunamadı.");
    }

    static void stokGuncelle() {
        System.out.print("Stok güncellenecek Kitap ID: ");
        String id = scanner.nextLine();
        for (Kitap k : kitaplar) {
            if (k.id.equals(id)) {
                System.out.println("Şu anki stok: " + k.stok);
                System.out.print("Yeni stok değeri: ");
                int yeniStok = scanner.nextInt(); scanner.nextLine();
                k.stok = yeniStok;
                kitaplariKaydet();
                System.out.println("Stok başarıyla güncellendi.");
                return;
            }
        }
        System.out.println("Kitap bulunamadı.");
    }

    static void uyeEkle() {
        System.out.print("Üye ID: ");
        String id = scanner.nextLine();
        System.out.print("Ad: ");
        String ad = scanner.nextLine();
        System.out.print("Soyad: ");
        String soyad = scanner.nextLine();
        uyeler.add(new Uye(id, ad, soyad));
        uyeleriKaydet();
        System.out.println("Üye eklendi.");
    }

    static void uyeListele() {
        System.out.println("--- Üyeler ---");
        for (Uye u : uyeler) System.out.println(u);
    }

    static void uyeSil() {
        System.out.print("Silinecek Üye ID: ");
        String id = scanner.nextLine();
        uyeler.removeIf(u -> u.id.equals(id));
        uyeleriKaydet();
        System.out.println("Üye silindi (varsa).");
    }

    static void uyeGuncelle() {
        System.out.print("Güncellenecek Üye ID: ");
        String id = scanner.nextLine();
        for (Uye u : uyeler) {
            if (u.id.equals(id)) {
                System.out.print("Yeni Ad: ");
                u.ad = scanner.nextLine();
                System.out.print("Yeni Soyad: ");
                u.soyad = scanner.nextLine();
                uyeleriKaydet();
                System.out.println("Üye güncellendi.");
                return;
            }
        }
        System.out.println("Üye bulunamadı.");
    }

    static void oduncVer() {
        System.out.print("Üye ID: ");
        String uyeId = scanner.nextLine();
        System.out.print("Kitap ID: ");
        String kitapId = scanner.nextLine();
        for (Kitap k : kitaplar) {
            if (k.id.equals(kitapId)) {
                if (k.stok > 0) {
                    k.stok--;
                    oduncler.add(new Odunc(uyeId, kitapId, new Date().toString()));
                    kitaplariKaydet();
                    oduncleriKaydet();
                    System.out.println("Ödünç verildi.");
                    return;
                } else {
                    System.out.println("Stok yok.");
                    return;
                }
            }
        }
        System.out.println("Kitap bulunamadı.");
    }

    static void iadeAl() {
        System.out.print("Üye ID: ");
        String uyeId = scanner.nextLine();
        System.out.print("Kitap ID: ");
        String kitapId = scanner.nextLine();
        boolean bulundu = false;
        Iterator<Odunc> iter = oduncler.iterator();
        while (iter.hasNext()) {
            Odunc o = iter.next();
            if (o.uyeId.equals(uyeId) && o.kitapId.equals(kitapId)) {
                iter.remove();
                bulundu = true;
                break;
            }
        }
        if (bulundu) {
            for (Kitap k : kitaplar) {
                if (k.id.equals(kitapId)) {
                    k.stok++;
                    break;
                }
            }
            kitaplariKaydet();
            oduncleriKaydet();
            System.out.println("İade alındı.");
        } else {
            System.out.println("Kayıt bulunamadı.");
        }
    }

    static void oduncleriListele() {
        System.out.println("--- Ödünç Kitaplar ---");
        for (Odunc o : oduncler) System.out.println(o);
    }

    static void kitaplariYukle() {
        try (BufferedReader br = new BufferedReader(new FileReader("kitaplar.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                kitaplar.add(new Kitap(d[0], d[1], d[2], Integer.parseInt(d[3])));
            }
        } catch (IOException e) {}
    }

    static void kitaplariKaydet() {
        try (PrintWriter pw = new PrintWriter("kitaplar.txt")) {
            for (Kitap k : kitaplar)
                pw.println(k.id + "," + k.ad + "," + k.yazar + "," + k.stok);
        } catch (IOException e) {}
    }

    static void uyeleriYukle() {
        try (BufferedReader br = new BufferedReader(new FileReader("uyeler.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                uyeler.add(new Uye(d[0], d[1], d[2]));
            }
        } catch (IOException e) {}
    }

    static void uyeleriKaydet() {
        try (PrintWriter pw = new PrintWriter("uyeler.txt")) {
            for (Uye u : uyeler)
                pw.println(u.id + "," + u.ad + "," + u.soyad);
        } catch (IOException e) {}
    }

    static void oduncleriYukle() {
        try (BufferedReader br = new BufferedReader(new FileReader("oduncler.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                oduncler.add(new Odunc(d[0], d[1], d[2]));
            }
        } catch (IOException e) {}
    }

    static void oduncleriKaydet() {
        try (PrintWriter pw = new PrintWriter("oduncler.txt")) {
            for (Odunc o : oduncler)
                pw.println(o.uyeId + "," + o.kitapId + "," + o.tarih);
        } catch (IOException e) {}
    }
}

