package repository;

import model.Uye;
import java.io.*;
import java.util.*;

public class UyeRepository {
    private final String DOSYA_ADI = "uyeler.txt";

    public List<Uye> yukle() {
        List<Uye> uyeler = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DOSYA_ADI))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 3) {
                    uyeler.add(new Uye(d[0], d[1], d[2]));
                }
            }
        } catch (IOException e) {}
        return uyeler;
    }

    public void kaydet(List<Uye> uyeler) {
        try (PrintWriter pw = new PrintWriter(DOSYA_ADI)) {
            for (Uye u : uyeler) {
                pw.println(u.id + "," + u.ad + "," + u.soyad);
            }
        } catch (IOException e) {}
    }
}