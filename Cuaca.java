import java.util.Random;

public class Cuaca {
    private String kondisi;

    public Cuaca() {
        // Random untuk menentukan cuaca
        Random random = new Random();
        int kondisiCuaca = random.nextInt(2); // 0 untuk cerah, 1 untuk hujan
        if (kondisiCuaca == 0) {
            this.kondisi = "Kemarau";
        } else {
            this.kondisi = "Hujan";
        }
    }

    public String getKondisi() {
        return kondisi;
    }

    public boolean isHujan() {
        return kondisi.equals("Hujan");
    }
}
