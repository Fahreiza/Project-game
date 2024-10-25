import java.util.ArrayList;
import java.util.List;

public class Lahan {
    private int kapasitas; // Kapasitas lahan untuk menanam tanaman
    private List<Tanaman> tanamanList; // List tanaman yang sedang ditanam di lahan

    public Lahan() {
        this.kapasitas = 2; // Kapasitas awal lahan adalah 2 tanaman
        this.tanamanList = new ArrayList<>();
    }

    // Mengembalikan kapasitas lahan saat ini
    public int getKapasitas() {
        return kapasitas;
    }

    // Mengecek apakah lahan sudah penuh atau belum
    public boolean isFull() {
        return tanamanList.size() >= kapasitas;
    }

    // Menambahkan tanaman ke lahan
    public void tanamTanaman(Tanaman tanaman) {
        if (!isFull()) {
            tanamanList.add(tanaman);
            System.out.println("Tanaman " + tanaman.getNama() + " berhasil ditanam.");
        } else {
            System.out.println("Lahan penuh! Tidak bisa menanam tanaman.");
        }
    }

    // Menyiram semua tanaman yang ada di lahan
    public void siramTanaman() {
        if (tanamanList.isEmpty()) {
            System.out.println("Tidak ada tanaman yang perlu disiram.");
        } else {
            for (Tanaman tanaman : tanamanList) {
                tanaman.siram();
            }
            System.out.println("Semua tanaman telah disiram.");
        }
    }

    // Memanen tanaman yang sudah bisa dipanen
    public void panenTanaman(Player player) {
        List<Tanaman> tanamanSiapPanen = new ArrayList<>();
        for (Tanaman tanaman : tanamanList) {
            if (tanaman.isSiapPanen()) {
                tanamanSiapPanen.add(tanaman);
            }
        }

        if (tanamanSiapPanen.isEmpty()) {
            System.out.println("Tidak ada tanaman yang siap dipanen.");
        } else {
            for (Tanaman tanaman : tanamanSiapPanen) {
                tanamanList.remove(tanaman);
                player.addInventory(tanaman); // Menambahkan hasil panen ke inventori pemain
                System.out.println("Tanaman " + tanaman.getNama() + " telah dipanen.");
            }
        }
    }

    // Meningkatkan kapasitas lahan
    public void upgradeLahan(int tambahanKapasitas) {
        kapasitas += tambahanKapasitas;
        System.out.println("Kapasitas lahan ditingkatkan. Kapasitas saat ini: " + kapasitas);
    }

    // Memproses hari berikutnya, meng-update pertumbuhan tanaman
    public void nextDay() {
        if (tanamanList.isEmpty()) {
            System.out.println("Tidak ada tanaman yang tumbuh di lahan.");
        } else {
            for (Tanaman tanaman : tanamanList) {
                tanaman.tumbuh();
            }
        }
    }

    // Mengecek apakah semua tanaman di lahan gagal
    public boolean isAllTanamanGagal() {
        for (Tanaman tanaman : tanamanList) {
            if (!tanaman.isGagalPanen()) {
                return false;
            }
        }
        return true;
    }

    // Mengecek apakah lahan kosong
    public boolean isEmpty() {
        return tanamanList.isEmpty();
    }
}
