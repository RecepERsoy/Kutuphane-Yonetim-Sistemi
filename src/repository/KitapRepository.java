package repository;

import model.Kitap;
import java.io.*;
import java.util.*;

public class KitapRepository {
    private final String DOSYA_ADI = "kitaplar.txt";

    public List<Kitap> yukle() {
        List<Kitap> kitaplar = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DOSYA_ADI))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 4) {
                    kitaplar.add(new Kitap(d[0], d[1], d[2], Integer.parseInt(d[3])));
                }
            }
        } catch (IOException e) {
        }
        return kitaplar;
    }

    public void kaydet(List<Kitap> kitaplar) {
        try (PrintWriter pw = new PrintWriter(DOSYA_ADI)) {
            for (Kitap k : kitaplar) {
                pw.println(k.id + "," + k.ad + "," + k.yazar + "," + k.stok);
            }
        } catch (IOException e) {
            System.out.println("Kitaplar kaydedilirken hata oluştu!");
        }
    }
}