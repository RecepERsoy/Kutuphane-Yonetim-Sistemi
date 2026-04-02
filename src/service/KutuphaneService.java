package service;

import java.util.*;
import model.*;
import repository.*;

public class KutuphaneService {
    private KitapRepository KitapRepo = new KitapRepository();
    private UyeRepository UyeRepo = new UyeRepository();
    private OduncRepository OduncRepo = new OduncRepository();

    private List<Kitap> kitaplar;
    private List<Uye> uyeler;
    private List<Odunc> oduncler;

    public KutuphaneService() {
        this.kitaplar = KitapRepo.yukle();
        this.uyeler = UyeRepo.yukle();
        this.oduncler = OduncRepo.yukle();
    }

    public void tumunuKaydet() {
        KitapRepo.kaydet(kitaplar);
        UyeRepo.kaydet(uyeler);
        OduncRepo.kaydet(oduncler);
    }

    public void kitapEkle(Kitap kitap) {
        kitaplar.add(kitap);
        KitapRepo.kaydet(kitaplar);
    }

    public List<Kitap> kitaplariGetir() {
        return kitaplar;
    }

    public void kitapSil(String id) {
        kitaplar.removeIf(k -> k.id.equals(id));
        KitapRepo.kaydet(kitaplar);
    }

    public boolean kitapGuncelle(String id, String yeniAd, String yeniYazar, int yeniStok) {
        for (Kitap k : kitaplar) {
            if (k.id.equals(id)) {
                k.ad = yeniAd;
                k.yazar = yeniYazar;
                k.stok = yeniStok;
                KitapRepo.kaydet(kitaplar);
                return true;
            }
        }
        return false;
    }

    public Kitap kitapBul(String id) {
        for (Kitap k : kitaplar) {
            if (k.id.equals(id)) return k;
        }
        return null;
    }

    public void uyeEkle(Uye uye) {
        uyeler.add(uye);
        UyeRepo.kaydet(uyeler);
    }

    public List<Uye> uyeleriGetir() {
        return uyeler;
    }

    public void uyeSil(String id) {
        uyeler.removeIf(u -> u.id.equals(id));
        UyeRepo.kaydet(uyeler);
    }

    public boolean uyeGuncelle(String id, String yeniAd, String yeniSoyad) {
        for (Uye u : uyeler) {
            if (u.id.equals(id)) {
                u.ad = yeniAd;
                u.soyad = yeniSoyad;
                UyeRepo.kaydet(uyeler);
                return true;
            }
        }
        return false;
    }

    public boolean oduncVer(String uyeId, String kitapId) {
        for (Kitap k : kitaplar) {
            if (k.id.equals(kitapId)) {
                if (k.stok > 0) {
                    k.stok--;
                    oduncler.add(new Odunc(uyeId, kitapId, new Date().toString()));
                    KitapRepo.kaydet(kitaplar);
                    OduncRepo.kaydet(oduncler);
                    return true;
                } else {
                    return false; 
                }
            }
        }
        return false; 
    }

    public boolean iadeAl(String uyeId, String kitapId) {
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
            KitapRepo.kaydet(kitaplar);
            OduncRepo.kaydet(oduncler);
            return true;
        }
        return false;
    }

    public List<Odunc> oduncleriGetir() {
        return oduncler;
    }
}