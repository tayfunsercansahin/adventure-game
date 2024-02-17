import java.lang.String;

public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "Tool Store");
    }

    @java.lang.Override
    public boolean onLocation() {
        System.out.println("Welcome to the tool store.");
        boolean showMenu = true;

        while (showMenu) {
            System.out.println("1 - Weapons");
            System.out.println("2 - Armors");
            System.out.println("3 - Logout");

            System.out.print("Please make a selection: ");
            int selectCase = this.input.nextInt();

            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Invalid value, please enter again: ");
                selectCase = this.input.nextInt();
            }

            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmors();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("We look forward to welcoming you back to our tool store.");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("WEAPONS");
        for (Weapons w : Weapons.weapons()) {
            System.out.println(w.getID() + " -\t(Weapon: " + w.getName() +
                                       " | Damage: " + w.getDamage() +
                                        " | Price: " + w.getPrice() + ")");
        }

        System.out.println("0 - Logout");
    }

    public void buyWeapon() {
        System.out.print("Choose a weapon: ");
        int selectedWeaponID = input.nextInt();

        while (selectedWeaponID < 0 || selectedWeaponID > Weapons.weapons().length) {
            System.out.println("Invalid value, please enter again: ");
            selectedWeaponID = input.nextInt();
        }

        if (selectedWeaponID != 0) {
            Weapons selectedWeapon = Weapons.getWeaponObjectByID(selectedWeaponID);

            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("You don't have enough money.");
                } else {
                    System.out.println("You purchased the " + selectedWeapon.getName());
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your remaining money is " + this.getPlayer().getMoney());
                    System.out.println("Your previous weapon: " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Your new weapon: " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }
    }

    public void printArmors() {
        System.out.println("ARMORS");
        for (Armors a : Armors.armors()) {
            System.out.println(a.getID() + " -\tArmor: " + a.getName() +
                                           " | Block: " + a.getBlock() +
                                           " | Price: " + a.getPrice());
        }

        System.out.println("0 - Logout");
    }

    public void buyArmor() {
        System.out.print("Choose a armor: ");
        int selectedArmorID = input.nextInt();

        while (selectedArmorID < 0 || selectedArmorID > Armors.armors().length) {
            System.out.print("Invalid value, please enter again: ");
            selectedArmorID = input.nextInt();
        }

        if (selectedArmorID != 0) {
            Armors selectedArmor = Armors.getArmorObjectByID(selectedArmorID);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("You don't have enough money.");
                } else {
                    System.out.println("You purchased the " + selectedArmor.getName());
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your remaining money is " + this.getPlayer().getMoney());
                    System.out.println("Your previous armor: " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Your new armor: " + this.getPlayer().getInventory().getArmor().getName());
                }
            }
        }
    }
}
