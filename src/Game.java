import java.util.Scanner;
import java.lang.String;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the adventure game!");
        System.out.print("Please enter a name: ");
        String playerName = input.nextLine();

        Player player = new Player(playerName);
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Dear " + player.getName() + ", welcome to this dark and foggy island! All that happens here is real...");
        System.out.println("---------------------------------------------------------------------------------------");
        player.selectChar();
        System.out.println("---------------------------------------------------------------------------------------");

        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println("Locations");
            System.out.println("1 - Safe House -> There are no enemies.");
            System.out.println("2 - Tool Store -> You can buy weapons or armors.");
            System.out.println("3 - Cave -> Award <Food>, be careful, a zombie may appear in front of you.");
            System.out.println("4 - Forest -> Award <Firewood>, be careful, a vampire may appear in front of you.");
            System.out.println("5 - River -> Award <Water>, be careful, a bear may appear in front of you.");
            System.out.println("0 - Logout -> Finish the game. ");
            System.out.print("Please select the location you want to go to: ");
            int selectLocation = input.nextInt();

            switch (selectLocation) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    System.out.println("Please enter a valid location.");
            }

            if (location == null) {
                System.out.println("You quickly gave up on this dark and misty island.");
                break;
            }

            if (!location.onLocation()) {
                System.out.println("You are dead, game over!");
                break;
            }

            System.out.println("---------------------------------------------------------------------------------------");
        }




    }
}
