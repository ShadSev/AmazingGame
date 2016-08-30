import com.googlecode.lanterna.terminal.Terminal;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016-08-30.
 */
public class UpdateScreen {

    public UpdateScreen(List<Obstacle> obstacles, Terminal terminal) {
        for (int j = 0; j < obstacles.size(); j++){
            terminal.clearScreen();
            terminal.moveCursor(obstacles.get(j).x,obstacles.get(j).y);
            terminal.putCharacter(obstacles.get(j).character);
        }

    }
}