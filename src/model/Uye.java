package model;

public class Uye {
    public String id, ad, soyad;

    public Uye(String id, String ad, String soyad) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
    }

    @Override
    public String toString() {
        return id + " - " + ad + " " + soyad;
    }
}