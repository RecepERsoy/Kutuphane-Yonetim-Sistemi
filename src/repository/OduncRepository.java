package repository;

import model.Odunc;
import java.io.*;
import java.util.*;

public class OduncRepository {
    private final String DOSYA_ADI = "oduncler.txt";

    public List<Odunc> yukle() {
        List<Odunc> oduncler = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DOSYA_ADI))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d.length == 3) {
                    oduncler.add(new Odunc(d[0], d[1], d[2]));
                }
            }
        } catch (IOException e) {}
        return oduncler;
    }

    public void kaydet(List<Odunc> oduncler) {
        try (PrintWriter pw = new PrintWriter(DOSYA_ADI)) {
            for (Odunc o : oduncler) {
                pw.println(o.uyeId + "," + o.kitapId + "," + o.tarih);
            }
        } catch (IOException e) {}
    }
}