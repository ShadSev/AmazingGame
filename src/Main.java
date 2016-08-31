import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Terminal terminal = TerminalFacade.createTerminal(System.in,
                System.out, Charset.forName("UTF8"));
        terminal.setCursorVisible(false);
        terminal.enterPrivateMode();

        Player player = new Player(15, 25);
        List<Obstacle> obstacles = new ArrayList<>();
        GameLogic gameLogic = new GameLogic();
        Print printer = new Print(terminal);

        boolean isGameOn = true;

        while (isGameOn) {

            Thread.sleep(55);
            isGameOn = gameLogic.checkTextActions(player);

            printer.printAll(player, obstacles);
            String currentMessage = gameLogic.checkCurrentMessage();
            printer.printCurrentMessage(currentMessage, gameLogic.messageX, gameLogic.messageY);

            obstacles = gameLogic.run(obstacles);
            GameLogic.movePlayer(player, terminal);
            GameLogic.detectCollision(obstacles, player);
        }
        printer.printGameOver();
    }
}


