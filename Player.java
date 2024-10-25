import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int age;
    private int coin;
    private List<Tanaman> inventory;

    // Konstruktor yang menerima nama, umur, dan koin awal
    public Player(String name, int age, int initialCoin) {
        this.name = name;
        this.age = age;
        this.coin = initialCoin;
        this.inventory = new ArrayList<>();
    }

    // Metode getter untuk atribut profil dan koin
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getCoin() {
        return coin;
    }

    public void addCoin(int amount) {
        this.coin += amount;
    }

    public List<Tanaman> getInventory() {
        return inventory;
    }

    public void addInventory(Tanaman tanaman) {
        inventory.add(tanaman);
    }

    public void clearInventory() {
        inventory.clear();
    }
}
