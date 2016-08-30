import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;



/**
 * Created by Administrator on 2016-08-30.
 */
public class Player {
    public int x;
    public int y = 25;
    public int points;
    public int life;

    public Player(int x, int y){
        this.x=x;
        this.y=y;
        this.points=0;
        this.life=1;
    }

}
