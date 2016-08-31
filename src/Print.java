import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.util.List;

/**
 * Created by Administrator on 2016-08-31.
 */
public class Print  {
    Terminal terminal;

    public Print(Terminal terminal) {
        this.terminal = terminal;
    }


    public void printObstacles(List<Obstacle> obstacles) {

        for (Obstacle obstacle: obstacles) {
           terminal.applyForegroundColor(obstacle.color.getRed(), obstacle.color.getGreen(), obstacle.color.getBlue());
            terminal.moveCursor(obstacle.x, obstacle.y);
            terminal.putCharacter(obstacle.character);
            terminal.applyForegroundColor(255,255,255);
        }
    }

    public void printPlayer(Player player) {
        terminal.moveCursor(player.x, player.y);
        terminal.putCharacter('M');
    }


    public int printGameOver () {
        printString( "Game Over!",  10, 10);
        return -1;
    }
    public void printWohoo()  {
        printString("Wohoo!", 10, 10);
    }
    public void printPlayerStats(Player player ) {
        printString( "Antal Poäng: " + player.points + "\nAntal Liv: " + player.life, 0, 0);
    }
    public void printCurrentMessage(String text) {
        if (text != null) {
            printString(text, 10, 10);
        }
    }
    public void printString (String text, int y, int x)  {
        int counter = 0;
        while (counter < text.length()) {
            terminal.applyForegroundColor(70, 140, 160);
            terminal.moveCursor(x++, y);
            terminal.putCharacter(text.charAt(counter++));
            terminal.applyForegroundColor(235, 235, 235);
        }
    }

    public void printBorder(){
        for (int x = 0; x <= 35; x++) {
            for (int y = 1; y <= 35; y++) {
                if(x == 0 || x == 35){
                    terminal.moveCursor(x, y);
                    terminal.putCharacter('\u2588');
                }
                else if (y==1||y==29){
                    terminal.moveCursor(x, y);
                    terminal.putCharacter('\u2588');
                }
            }
        }
   }


}
