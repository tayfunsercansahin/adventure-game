import java.util.Random;

public abstract class BattleLocation extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLocation(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @java.lang.Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("You are here right now: " + this.getName());
        System.out.println("Be careful. Here, a " + obsNumber + " " + this.getObstacle().getName() + " is living.");
        System.out.print("<F>ight or <A>void: ");
        String selectCase = input.nextLine().toUpperCase();

        if (selectCase.equals("F") && combat(obsNumber)) {
                System.out.println("You defeated all the obstacles in the " + this.getName());
                return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You died.");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<H>it or <A>void: ");
                String selectCombat = input.nextLine().toUpperCase();

                if (selectCombat.equals("H")) {
                    System.out.println("You hit.");
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();

                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println("The obstacle hit you.");

                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();

                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }

                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                } else if (selectCombat.equals("A")){
                    return false;
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You defeated the monster. You earned " + this.getObstacle().getAward() + " coins.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Your current money: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Your health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + "'s health: " + this.getObstacle().getHealth());
    }

    public void playerStats() {
        System.out.println("Character values: " + "\t Health: " + this.getPlayer().getHealth() +
                           "\t Weapon: " + this.getPlayer().getInventory().getWeapon().getName() +
                           "\t Damage: " + this.getPlayer().getDamage() +
                           "\t Armor: " + this.getPlayer().getInventory().getArmor().getName() +
                           "\t Block: " + this.getPlayer().getInventory().getArmor().getBlock() +
                           "\t Money: " + this.getPlayer().getMoney()
        );
    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName() + " values: " + "\t Health: " + this.getObstacle().getHealth() +
                           "\t Damage: " + this.getObstacle().getDamage() +
                           "\t Award: " + this.getObstacle().getAward()
        );
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
