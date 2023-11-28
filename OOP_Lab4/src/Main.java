import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel {
    private int x = 100;
    private int y = 100;
    private int radius = 50;
    private int dx = 2;
    private int dy = 2;
    private boolean isGrowing = true;

    public Main() {
        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x += dx;
                y += dy;

                if (x - radius < 0 || x + radius > getWidth()) {
                    dx = -dx;
                    isGrowing = false; // меняем флаг на false, когда шар достигает границы
                }

                if (y - radius < 0 || y + radius > getHeight()) {
                    dy = -dy;
                    isGrowing = false; // меняем флаг на false, когда шар достигает границы
                }

                if (isGrowing) {
                    radius += 1; // увеличиваем радиус, когда шар достигает границы
                } else {
                    radius -= 1; // уменьшаем радиус, когда шар приближается к центру
                    if (radius <= 0) {
                        isGrowing = true; // меняем флаг на true, когда радиус становится равным нулю
                    }
                }

                repaint();
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bouncing Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Main());
        frame.setVisible(true);
    }
}