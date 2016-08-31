import java.awt.*;

public abstract class Obstacle {
     public int x;
     public int y;
     public char character;
     public Color color;

    public void setColor(int hexColor) {
        color= new Color(hexColor);
    }
}
