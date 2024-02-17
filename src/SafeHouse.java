import java.lang.String;

public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player) {
        super(player, "Safe House");
    }

    @java.lang.Override
    public boolean onLocation() {
        System.out.println("You are in a safe house.");
        System.out.println("Renewed your health.");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());

        return true;
    }
}
