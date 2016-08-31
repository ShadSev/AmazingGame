import java.awt.*;



/**
 * Created by Administrator on 2016-08-30.
 */
public class Comet extends Obstacle {

    public Comet (int x, char character) {
        super.x=x;
        super.y=2;
        super.character=character;
        super.color= new Color(0xff6666);
        }
}
