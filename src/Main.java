import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {


    public static void main(String[] args) throws InterruptedException {
        Player player = new Player(15, 25);
        List<Obstacle> obstacles = new ArrayList<>();
        Terminal terminal = TerminalFacade.createTerminal(System.in,
                System.out, Charset.forName("UTF8"));
        terminal.enterPrivateMode();
        terminal.setCursorVisible(false);
        GameLogic gameLogic = new GameLogic();

        Print printer= new Print(terminal);
        int counter = 0;

        while (counter!=-1) {

            counter++;
            Thread.sleep(85);
            terminal.clearScreen();
            printer.printPlayerStats(player);
            printer.printObstacles(obstacles);
            printer.printBorder();
            printer.printPlayer(player);

            counter=gameLogic.checkTextActions(player, counter);
            String currentMessage = gameLogic.checkCurrentMessage();
            printer.printCurrentMessage(currentMessage);

            if (counter %2 == 0) {
                obstacles = gameLogic.run(obstacles); //Returna a list of obstacles
            }
            GameLogic.movePlayer(player, terminal);
            GameLogic.detectCollision(obstacles, player);




        }
        printer.printGameOver();
    }

}

