import java.awt.*;


public class Goodie extends Obstacle {

    public Goodie (int x, char character) {
        super.x=x;
        super.y=2;
        super.character=character;
        super.color= new Color(0x80ff80);
    }
}
