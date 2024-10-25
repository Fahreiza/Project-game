public class Tanaman {
    protected String nama;
    protected int hargaJual;
    protected int umurPanen;  // Umur panen dalam hari
    protected int umur;       // Umur tanaman dalam hari
    protected boolean disiram; // Status apakah tanaman sudah disiram hari ini

    public Tanaman(String nama, int hargaJual, int umurPanen) {
        this.nama = nama;
        this.hargaJual = hargaJual;
        this.umurPanen = umurPanen;
        this.umur = 0;        // Awalnya tanaman berumur 0 hari
        this.disiram = false; // Tanaman belum disiram di awal
    }

    // Menyiram tanaman
    public void siram() {
        if (!disiram) {
            this.disiram = true;
            System.out.println("Tanaman " + nama + " telah disiram.");
        } else {
            System.out.println("Tanaman " + nama + " sudah disiram hari ini.");
        }
    }

    // Tumbuh setiap hari, hanya bertambah umur jika sudah disiram
    public void tumbuh() {
        if (disiram) {
            umur++;
            disiram = false; // Reset status siram setiap hari
            System.out.println("Tanaman " + nama + " tumbuh. Umur sekarang: " + umur);
        } else {
            System.out.println("Tanaman " + nama + " tidak tumbuh karena tidak disiram.");
        }
    }

    public int getHargaJual() {
        return hargaJual;
    }

    // Mengecek apakah tanaman siap dipanen
    public boolean isSiapPanen() {
        return umur >= umurPanen;
    }

    // Mendapatkan umur tanaman
    public int getUmur() {
        return umur;
    }

    public String getNama() {
        return nama;
    }

    public int getUmurPanen() {
        return umurPanen;
    }

    // Metode grow untuk pengurangan umur panen
    public void grow() {
        tumbuh();
    }

    // Mengecek apakah tanaman gagal dipanen
    public boolean isGagalPanen() {
        return umur > umurPanen + 2;  // Jika tanaman melebihi 2 hari setelah umur panen
    }
}
