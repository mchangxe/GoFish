import java.util.Scanner;

/**
 * Created by Museum2015 on 8/10/2016.
 */
public class Main {
    public static void main(String args[]) throws Exception {
        //setup game and prompt user to input number of players
        System.out.println("Welcome to the game of Go Fish!");
        System.out.println("How many players want to play? please input an integer number, ideally less than 5");
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers = scanner.nextInt();
        GoFishGames newGame = new GoFishGames(numberOfPlayers);
        Player[] player = newGame.getAllPlayers();

        //game starts by everyone drawing a card
        for(Player curr: player){
            newGame.drawCard(curr);
            newGame.drawCard(curr);
            newGame.drawCard(curr);
        }

        int whosTurn = 0;

        while(!newGame.checkDeckEmpty()){

            //the current player picks an opponent
            int opponent = player[whosTurn].picksOpponent(numberOfPlayers);

            int cardRank = player[whosTurn].askForCard(opponent);

            int numberOfCardsToGive = player[opponent-1].giveCard(cardRank);

            if(numberOfCardsToGive != 0){
                //the player gets some cards from his opponent
                player[whosTurn].receiveCard(cardRank, numberOfCardsToGive);
                player[whosTurn].checkBook();
            }else{
                //GO FISH!!!
                newGame.drawCard(player[whosTurn]);

                whosTurn++;
                if (whosTurn >= numberOfPlayers){
                    whosTurn = 0;
                }
            }
        }

        System.out.println("The game has ended!");

        //decide winner
        int winner = 1;
        for (Player p: player){
            System.out.println("Player"+p.getPlayerNumber()+" has "+p.getPoints()+" points");
            if (p.getPoints() > winner){
                winner = p.getPlayerNumber();
            }
        }
        System.out.println("The winner is Player" + winner + "!");

    }
}
