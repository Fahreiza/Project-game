public class Waktu {
    private int hari;

    public Waktu() {
        this.hari = 1; // Hari dimulai dari 1
    }

    public int getHari() {
        return hari;
    }

    public void nextDay() {
        hari++; // Tambah hari saat hari berganti
    }
}
