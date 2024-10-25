import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta input profil pemain
        System.out.println("=======================================================================");
        System.out.println("                         WELCOME TO HARVEST GAME                       ");
        System.out.println("=======================================================================");
        System.out.print("Masukkan nama Anda: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan umur Anda: ");
        int umur = scanner.nextInt();

        // Membuat pemain dengan profil dan 5000 coin
        Player player = new Player(nama, umur, 5000); // Memberikan 5000 coin ke pemain

        // Tampilkan profil pemain
        System.out.println("Nama: " + player.getName());
        System.out.println("Umur: " + player.getAge());
        System.out.println("Anda mendapatkan 5000 coin untuk memulai permainan.\n");

        // Memulai game        
        FarmGame farmGame = new FarmGame(player);
        farmGame.startGame();
    }
}
