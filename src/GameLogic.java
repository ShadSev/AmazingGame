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


    public List<Obstacle> run(List<Obstacle> obstacles) {
        moveObstacle(obstacles);
        addObstacle(obstacles);
        removeObstacleBelow(obstacles);
        return obstacles;
    }

    public static void movePlayer(Player player, Terminal terminal) throws InterruptedException {
        Key key = terminal.readInput();
        if (key == null) {
            return;
        }

        switch (key.getKind()) {
            case ArrowRight:
                player.x += 1;
                break;
            case ArrowLeft:
                player.x -= 1;
                break;
            case ArrowDown:
                player.y = player.y + 1;
                break;
            case ArrowUp:
                player.y = player.y - 1;
                break;
            default:
                break;
        }

    }

    public static void detectCollision(List<Obstacle> obstacles, Player player) throws InterruptedException {
        char test='j';
        for (Obstacle obstacle : obstacles) {
            if (obstacle.x == player.x && obstacle.y == player.y) {
                test = obstacle.character;
                if (test == 'X') {
                    obstacle.character='8';
                    player.life-=1;
                } else if (test == '\u263a') {
                    player.points += 1;
                    obstacle.character='\u263b';
                    if(player.points%3==0){
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
            obstacles.add(new Comet(random.nextInt(35), 'X', "Black"));

        if ((random.nextInt(18)) % 9 == 0)
            obstacles.add(new Goodie(random.nextInt(35), '\u263a', "Black"));
    }


    private void removeObstacleBelow(List<Obstacle> obstacles) {
        while (obstacles.size() > 0 && obstacles.get(0).y > 30) {
            obstacles.remove(0);
        }

    }



}
