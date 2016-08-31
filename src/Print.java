import com.googlecode.lanterna.terminal.Terminal;
import java.util.List;

public class Print  {
    Terminal terminal;

    public Print(Terminal terminal) {
        this.terminal = terminal;
    }
    public void printAll(Player player, List<Obstacle> obstacles) {
        terminal.clearScreen();
        printPlayerStats(player);
        printObstacles(obstacles);
        printBorder();
        printPlayer(player);
    }

    private void printObstacles(List<Obstacle> obstacles) {

        for (Obstacle obstacle: obstacles) {
           terminal.applyForegroundColor(obstacle.color.getRed(), obstacle.color.getGreen(), obstacle.color.getBlue());
            terminal.moveCursor(obstacle.x, obstacle.y);
            terminal.putCharacter(obstacle.character);
            terminal.applyForegroundColor(255,255,255);
        }
    }

    public void printPlayer(Player player) {
        terminal.moveCursor(player.x, player.y);
        terminal.putCharacter('۩');
    }


    public int printGameOver () {
        printString( "Game Over!",  10, 10);
        return -1;
    }

    private void printPlayerStats(Player player ) {
        printString( "Antal Poäng: " + player.points + "\nAntal Liv: " + player.life, 0, 0);
    }
    public void printCurrentMessage(String text, int x, int y) {
        if (text != null) {
            printString(text, y, x);
        }
    }
    private void printString (String text, int y, int x)  {
        int counter = 0;
        while (counter < text.length()) {
            terminal.applyForegroundColor(70, 140, 160);
            terminal.moveCursor(x++, y);
            terminal.putCharacter(text.charAt(counter++));
            terminal.applyForegroundColor(235, 235, 235);
        }
    }

    private void printBorder(){
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
