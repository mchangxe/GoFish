import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Created by Museum2015 on 8/10/2016.
 */
public class Main {
    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);

        //ask for location
        System.out.println("English or Chinese?");
        String userInput = scanner.nextLine();
        ResourceBundle messages = GoFishGames.setLocation(userInput);

        //setup game and prompt user to input number of players
        System.out.println(messages.getString("welcome"));
        System.out.println(messages.getString("how.many.smart"));
        int numberOfSmartPlayers = scanner.nextInt();
        System.out.println(messages.getString("how.many.stupid"));
        int numberOfStupidPlayers = scanner.nextInt();

        GoFishGames.mainGame(numberOfStupidPlayers, numberOfSmartPlayers, messages);
    }
}
