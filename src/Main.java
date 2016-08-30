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
        int counter = 0;
        boolean kör = true;


        int point = 0;
        while (kör) {
            counter++;
            Thread.sleep(65);
            if (counter == 2) {
                obstacles = gameLogic.run(obstacles); //Returna a list of obstacles
                counter = 0;
            }
            printObstacles(obstacles, terminal);
            GameLogic.movePlayer(player, terminal);
            printPlayer(player, terminal);
            GameLogic.detectCollision(obstacles, player);
            if (player.life < 1) {
                printString(terminal, "Game Over!" + "\nPoints: " + player.points, 10, 10);
                kör = false;
            }
            if (player.points > point) {
                printString(terminal, "Bingo!", 10, 10);
                point = player.points;
            }
            printString(terminal, "Antal Poäng: " + player.points + "\nAntal Liv: " + player.life, 0, 0);
        }
    }

    public static void printObstacles(List<Obstacle> obstacles, Terminal terminal) {
        terminal.clearScreen();
        for (int j = 0; j < obstacles.size(); j++) {
            terminal.moveCursor(obstacles.get(j).x, obstacles.get(j).y);
            terminal.putCharacter(obstacles.get(j).character);

        }
    }

    public static void printPlayer(Player player, Terminal terminal) {
        terminal.moveCursor(player.x, player.y);
        terminal.putCharacter('M');
    }

    public static void printString(Terminal terminal, String text, int y, int x) throws InterruptedException {
        int counter = 0;
        while (counter < text.length()) {

            terminal.applyForegroundColor(70, 140, 160);
            terminal.moveCursor(x++, y);
            terminal.putCharacter(text.charAt(counter++));
            terminal.applyForegroundColor(235, 235, 235);


        }

    }
}

