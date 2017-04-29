import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lauraccs on 4/15/17.
 */
public class Snake implements ActionListener, KeyListener{
    public JFrame jFrame;

    public boolean over = false, paused;

    public Toolkit toolkit;

    public static Snake snake;

    public RenderPanel renderPanel;

    public Timer timer = new Timer(20,this);

    public ArrayList<Point> snakeParts = new ArrayList<Point>();

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public int ticks = 0, direction = DOWN, score, tailLength=10, time;

    public Point head, cherry;

    public Random random;

    public Dimension dim;

    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        toolkit = Toolkit.getDefaultToolkit();
        jFrame = new JFrame("Snake");
        jFrame.setVisible(true);
        jFrame.setSize(800,650);
        jFrame.setResizable(false);
        jFrame.setLocation(dim.width/2-jFrame.getWidth()/2,dim.height/2-jFrame.getHeight()/2);
        jFrame.add(renderPanel = new RenderPanel());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addKeyListener(this);
        startGame();



    }

    public void startGame(){
        over = false;
        paused = false;
        time = 0;
        score = 0;
        tailLength = 14;
        direction = DOWN;
        head = new Point(0,-1);
        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(79),random.nextInt(66));
//        System.out.println(cherry.x+", " + cherry.y);
//        for (int i = 0; i < tailLength; i++){
//            snakeParts.add(new Point(head.x,head.y));
//        }
        timer.start();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println(cherry.x+", " + cherry.y);
        renderPanel.repaint();
        ticks+=4;

        if (ticks%10 == 0 && head != null && !over && !paused){
            time++;

            snakeParts.add(new Point(head.x,head.y));

            if(direction == UP) {
                if(head.y - 1 >= 0 && noTailAt(head.x, head.y - 1)) {
                    head = new Point(head.x, head.y - 1);
                }else {
                    over = true;
                }
            }
            if(direction == DOWN) {
                if(head.y + 1 < 67 && noTailAt(head.x, head.y + 1)) {
                    head = new Point(head.x, head.y + 1);
                }else {
                    over = true;
                }
            }
            if(direction == LEFT) {
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y)) {
                   head = new Point(head.x - 1, head.y);
                }else {
                    over = true;
                }
            }
            if(direction == RIGHT) {
                if(head.x + 1 < 80 && noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                }else {
                    over = true;
                }
            }
            if (snakeParts.size() > tailLength){
                snakeParts.remove(0);
            }

//            for(int i = 0;i <tailLength;i++){
//                snakeParts.remove(i);
//            }
            //snake extends without this
//            snakeParts.remove(0);

            if(cherry != null){
                if(head.equals(cherry)){
                    score += 10;
                    tailLength++;
                    cherry.setLocation(random.nextInt(79),random.nextInt(66));
                }

            }
        }
    }


    public boolean noTailAt(int x, int y)
    {
        for (Point point : snakeParts)
        {
            if (point.equals(new Point(x, y)))
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        snake =  new Snake();


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();

        if(i==KeyEvent.VK_LEFT && direction != RIGHT){
            direction = LEFT;
        }
        if(i==KeyEvent.VK_RIGHT && direction != LEFT){
            direction = RIGHT;
        }
        if(i==KeyEvent.VK_UP && direction != DOWN){
            direction = UP;
        }
        if(i==KeyEvent.VK_DOWN && direction != UP){
            direction = DOWN;
        }
        if (i==KeyEvent.VK_SPACE){
            if (over) {
                startGame();
            }else {
                paused = !paused;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
