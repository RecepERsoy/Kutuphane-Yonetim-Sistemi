package model;

public class Odunc {
    public String uyeId, kitapId, tarih;

    public Odunc(String uyeId, String kitapId, String tarih) {
        this.uyeId = uyeId;
        this.kitapId = kitapId;
        this.tarih = tarih;
    }

    @Override
    public String toString() {
        return "Üye: " + uyeId + ", Kitap: " + kitapId + ", Tarih: " + tarih;
    }
}