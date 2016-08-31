import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2016-08-30.
 */
public class GameLogic {
    public List<Obstacle> obstacles;
    public int wohooStay=0;
    public int point=0;
    public int speed=7;
    public int counter=0;
    public int messageX;
    public int messageY;


    public List<Obstacle> run(List<Obstacle> obstacles) {
        if (counter % speed == 0) {
            moveObstacle(obstacles);
            addObstacle(obstacles);
            removeObstacleBelow(obstacles);

            if (counter % 90 == 0 && speed > 2) {
                speed--;
            }
        }
        counter++;
        return obstacles;

    }

    public static void movePlayer(Player player, Terminal terminal) throws InterruptedException {
        Key key = terminal.readInput();
        if (key == null) {
            return;
        }

        switch (key.getKind()) {
            case ArrowRight:
                if(player.x<34)
                player.x += 1;
                break;
            case ArrowLeft:
                if(player.x>1)
                player.x -= 1;
                break;
            case ArrowDown:
                if(player.y<28)
                player.y = player.y + 1;
                break;
            case ArrowUp:
                if(player.y>2)
                player.y = player.y - 1;
                break;
            default:
                break;
        }

    }
    public  boolean checkTextActions (Player player) throws InterruptedException {

        if (player.life < 1) {
            return false;
        }
        if (player.points > point) {
            wohooStay=10;
            point = player.points;
            messageX = player.x;
            messageY = player.y;
        }
        if(wohooStay>0){
            wohooStay--;
        }
        return true;
    }
    public String checkCurrentMessage() {
        if (wohooStay != 0) {
            return "Wohoo!";
        }
        return null;
    }

    public static void detectCollision(List<Obstacle> obstacles, Player player) throws InterruptedException {
        char test;
        for (Obstacle obstacle : obstacles) {
            if (obstacle.x == player.x && obstacle.y == player.y) {
                test = obstacle.character;
                if (test == 'X') {
                    obstacle.character='8';
                    player.life-=1;
                    obstacle.setColor(0xbfbfbf);
                } else if (test == '\u263a') {
                    player.points += 1;
                    obstacle.character='\u263b';
                    obstacle.setColor(0xe6ecff);
                    if(player.points%4==0){
                        player.life++;
                    }
                }
            }
        }
    }


    private void moveObstacle(List<Obstacle> obstacles) {
        this.obstacles = obstacles;
        for (Obstacle obstacle : obstacles) {

            obstacle.y += 1;

        }
    }

    private void addObstacle(List<Obstacle> obstacles) {
        Random random = new Random();
        if ((random.nextInt(11)) % 3 == 0)
            obstacles.add(new Comet(random.nextInt(35), 'X'));

        if ((random.nextInt(18)) % 9 == 0)
            obstacles.add(new Goodie(random.nextInt(35), '\u263a'));
    }


    private void removeObstacleBelow(List<Obstacle> obstacles) {
        while (obstacles.size() > 0 && obstacles.get(0).y > 28) {
            obstacles.remove(0);
        }

    }



}
