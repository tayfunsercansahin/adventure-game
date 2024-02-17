import java.lang.String;

public class Inventory {
    private Weapons weapon;
    private Armors armor;

    public Inventory() {
        this.weapon = new Weapons("Punch", -1, 0, 0);
        this.armor = new Armors(-1, "Rag", 0, 0);
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public Armors getArmor() {
        return armor;
    }

    public void setArmor(Armors armor) {
        this.armor = armor;
    }
}
