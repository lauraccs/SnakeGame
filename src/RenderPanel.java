import javax.swing.*;
import java.awt.*;

/**
 * Created by lauraccs on 4/15/17.
 */
public class RenderPanel extends JPanel {

    public static int curColor = 8900331;
//    public static Color green = new Color(convert from hex to dec color code)

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.setColor(Color.black);
        g.fillRect(0,0,800,650);
       g.setColor(new Color(curColor));
//        curColor++;
//        these two can be used to make the color gradually go from black to blue
//        make sure to remove other setcolor method above if these two lines are used
        Snake snake = Snake.snake;
        g.setColor(Color.BLUE);
        for (Point point : snake.snakeParts){
            g.setColor(Color.BLUE);
            g.fillRect(point.x * Snake.SCALE,point.y * Snake.SCALE,Snake.SCALE,Snake.SCALE);

        }
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);

        g.setColor(Color.CYAN);

        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);



    }
}
