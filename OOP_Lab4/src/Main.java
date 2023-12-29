import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Класс Main наследуется от JPanel
public class Main extends JPanel {
    // Координаты центра шара
    private int x = 100;
    private int y = 100;
    // Радиус шара
    private int radius = 50;
    // Скорости движения по осям x и y
    private int dx = 2;
    private int dy = 2;
    // Флаг, указывающий, растет ли шар
    private boolean isGrowing = true;

    // Конструктор класса Main
    public Main() {
        // Создаем таймер, который будет вызываться каждые 20 миллисекунд
        Timer timer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Изменяем координаты центра шара
                x += dx;
                y += dy;

                // Если шар достигает границы экрана по оси x, меняем направление движения
                if (x - radius < 0 || x + radius > getWidth()) {
                    dx = -dx;
                    isGrowing = false;
                }

                // Если шар достигает границы экрана по оси y, меняем направление движения
                if (y - radius < 0 || y + radius > getHeight()) {
                    dy = -dy;
                    isGrowing = false;
                }

                // Если шар растет, увеличиваем его радиус
                if (isGrowing) {
                    radius += 1;
                }
                // Если шар уже не растет, уменьшаем его радиус
                else {
                    radius -= 1;
                    // Если радиус стал меньше или равен 0, меняем направление движения
                    if (radius <= 0) {
                        isGrowing = true;
                    }
                }

                // Перерисовываем компонент
                repaint();
            }
        });

        // Запускаем таймер
        timer.start();
    }

    // Переопределяем метод, отвечающий за отрисовку компонента
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    // Точка входа в программу
    public static void main(String[] args) {
        // Создаем окно
        JFrame frame = new JFrame("OOP_Lab4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        // Добавляем нашу панель в окно
        frame.add(new Main());
        frame.setVisible(true);
    }
}