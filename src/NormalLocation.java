import java.lang.String;

public abstract class NormalLocation extends Location {
    public NormalLocation(Player player, String name) {
        super(player, name);
    }

    @java.lang.Override
     public boolean onLocation() {
        return true;
     }
}
