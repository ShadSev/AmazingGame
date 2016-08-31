import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        Player player = new Player(15, 25);
        Print printer = new Print();
        GameLogic gameLogic = new GameLogic();
        List<Obstacle> obstacles = new ArrayList<>();

        boolean isGameOn = true;

        while (isGameOn) {

            Thread.sleep(25);
            isGameOn = gameLogic.checkTextActions(player);

            printer.printAll(player, obstacles);
            String currentMessage = gameLogic.checkCurrentMessage();
            printer.printCurrentMessage(currentMessage, gameLogic.messageX, gameLogic.messageY);

            obstacles = gameLogic.run(obstacles);
            GameLogic.movePlayer(player, printer.terminal);
            GameLogic.detectCollision(obstacles, player);
        }
        printer.printGameOver();
    }
}


