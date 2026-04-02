package model;
public class Kitap {
    public String id, ad, yazar;
    public int stok;
    public Kitap(String id, String ad, String yazar, int stok) {
        this.id = id; this.ad = ad; this.yazar = yazar; this.stok = stok;
    }
    @Override
    public String toString() { return id + " - " + ad + " - " + yazar + " - Stok: " + stok; }
}