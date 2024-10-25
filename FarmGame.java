import java.util.Scanner;

public class FarmGame {
    private Player player;
    private Lahan lahan;
    private Waktu waktu;
    private Toko toko;
    private Cuaca cuaca;
    private boolean gameOver; 

    public FarmGame(Player player) {
        this.player = player;
        this.lahan = new Lahan();
        this.waktu = new Waktu();
        this.toko = new Toko(player);
        this.cuaca = new Cuaca(); // Cuaca dimulai pada hari pertama
        this.gameOver = false;  
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

            while (!gameOver) {
            // Tampilkan profil pemain dan jumlah coin
            System.out.println("\n=== Profil Pemain ===");
            System.out.println("Nama: " + player.getName());
            System.out.println("Coin: " + player.getCoin());
            System.out.println("======================\n");

            System.out.println("Hari ke-" + waktu.getHari() + " | Cuaca: " + cuaca.getKondisi());
            System.out.println("Kapasitas lahan saat ini: " + lahan.getKapasitas());
            System.out.println("1. Tanam Tanaman\n2. Siram Tanaman\n3. Panen\n4. Next Day\n5. Buka Toko\n6. Upgrade Lahan");
            System.out.print("Masukan Pilihan Anda: ");
            int choice = scanner.nextInt();

                switch (choice){
                case 1:
                    if (lahan.isFull()) {
                        System.out.println("Lahan penuh, tidak bisa menanam tanaman baru.");
                    } else if (player.getInventory().isEmpty()) {
                        System.out.println("\nAnda tidak memiliki benih di inventori. Silakan beli di toko.");
                    } else {
                        System.out.println("Pilih tanaman untuk ditanam:");
                        for (int i = 0; i < player.getInventory().size(); i++) {
                            System.out.println((i + 1) + ". " + player.getInventory().get(i).getNama());
                        }
                        int pilihanTanaman = scanner.nextInt();
                        if (pilihanTanaman > 0 && pilihanTanaman <= player.getInventory().size()) {
                            Tanaman tanamanDipilih = player.getInventory().get(pilihanTanaman - 1);
                            lahan.tanamTanaman(tanamanDipilih);
                            player.getInventory().remove(tanamanDipilih);
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                    }
                    break;
                case 2:
                    if (cuaca.isHujan()) {
                        System.out.println("Tanaman sudah disiram oleh hujan.");
                    } else {
                        lahan.siramTanaman();
                    }
                    break;
                case 3:
                    lahan.panenTanaman(player); // Pastikan player disertakan untuk menyimpan hasil panen
                    break;
                case 4:
                    waktu.nextDay(); // Panggil nextDay pada objek Waktu
                    lahan.nextDay(); // Panggil nextDay pada objek Lahan
                    cuaca = new Cuaca(); // Update cuaca setiap hari
                    System.out.println("Hari berganti, hari sekarang: " + waktu.getHari());
                    System.out.println("Cuaca hari ini: " + cuaca.getKondisi());
                    if (!lahan.isEmpty() && cuaca.isHujan()) {
                        System.out.println("Tanaman mendapatkan air karena hujan.");
                        lahan.siramTanaman(); // Tanaman disiram otomatis jika hujan
                    }
                    break;
                case 5:
                    bukaToko();
                    break;
                case 6:
                    upgradeLahan();
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        System.out.println("Game Over. Terima kasih sudah bermain.");
    }
    private void cekGameOver() {
        if (player.getCoin() == 0 && lahan.isAllTanamanGagal()) {  // Cek jika coin 0 dan semua tanaman gagal
            gameOver = true;  // Set game over menjadi true untuk menghentikan permainan
            System.out.println("Semua tanaman gagal dipanen dan koin Anda 0.");
        }
    }

    private void bukaToko() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=====================================================================================");
        System.out.println("                            Selamat datang di toko.                                  ");
        System.out.println("=====================================================================================");
        System.out.println("1. Beli benih\n2. Jual hasil panen\n3. Keluar");
        System.out.print("Masukan Pilihan Anda: ");
        int tokoChoice = scanner.nextInt();
    
        switch (tokoChoice) {
            case 1:
                System.out.println("Pilih benih yang ingin dibeli:");
                System.out.println("1. Padi (5000 coin)\n2. Jagung (10000 coin)\n3. Tomat (20000 coin)");
                System.out.print("Masukan Pilihan Anda: ");
                int beliBenihChoice = scanner.nextInt();
                switch (beliBenihChoice) {
                    case 1:
                        if (player.getCoin() >= 5000) {
                            player.addCoin(-5000); // Kurangi koin
                            player.getInventory().add(new Padi());
                            System.out.println("Anda membeli benih Padi.");
                        } else {
                            System.out.println("Koin Anda tidak cukup.");
                        }
                        break;
                    case 2:
                        if (player.getCoin() >= 10000) {
                            player.addCoin(-10000);
                            player.getInventory().add(new Jagung());
                            System.out.println("Anda membeli benih Jagung.");
                        } else {
                            System.out.println("Koin Anda tidak cukup.");
                        }
                        break;
                    case 3:
                        if (player.getCoin() >= 20000) {
                            player.addCoin(-20000);
                            player.getInventory().add(new Tomat());
                            System.out.println("Anda membeli benih Tomat.");
                        } else {
                            System.out.println("Koin Anda tidak cukup.");
                        }
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
                break;
            case 2:
                jualHasilPanen();  // Panggil method untuk menjual hasil panen
                break;
            case 3:
                System.out.println("Keluar dari toko.");
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }
    }
        
    private void jualHasilPanen() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Tidak ada hasil panen yang bisa dijual.");
        } else {
            int totalProfit = 0;
            
            // Menghitung total keuntungan dari hasil panen
            for (Tanaman hasilPanen : player.getInventory()) {
                totalProfit += hasilPanen.getHargaJual(); // Pastikan setiap tanaman punya metode getHargaJual
            }
    
            // Tambahkan coin ke pemain
            player.addCoin(totalProfit);
    
            // Kosongkan inventori pemain setelah panen terjual
            player.clearInventory();
    
            System.out.println("Anda berhasil menjual hasil panen dan mendapatkan " + totalProfit + " coin.");
            System.out.println("Coin Anda saat ini: " + player.getCoin());
        }
    }
    
        

    private void upgradeLahan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Biaya upgrade lahan adalah 10.000 coin. Apakah Anda ingin melanjutkan? (1. Ya / 2. Tidak)");
        int choice = scanner.nextInt();
        if (choice == 1) {
            if (player.getCoin() >= 10000) {
                player.addCoin(-10000);
                lahan.upgradeLahan(1); // Upgrade lahan, meningkatkan kapasitas sebesar 1
                System.out.println("Lahan berhasil di-upgrade.");
            } else {
                System.out.println("Koin Anda tidak cukup untuk melakukan upgrade.");
            }
        } else {
            System.out.println("Upgrade lahan dibatalkan.");
        }
    }
}
