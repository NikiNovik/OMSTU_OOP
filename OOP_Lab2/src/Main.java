import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // Это регулярное выражение для проверки валидности E-mail
        // Оно проверяет, чтобы E-mail содержал только буквы, цифры, точки, подчеркивания, проценты, плюсы и минусы
        // Затем оно проверяет, что после @ есть точки или цифры
        // И наконец, оно проверяет, что после последней точки есть 2 или более буквы
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        Scanner scanner = new Scanner(System.in);
        String email;

        // Это бесконечный цикл, который будет продолжаться, пока пользователь не введет "exit"
        while (true) {
            // Читаем ввод пользователя
            System.out.println("Введите E-mail или 'exit' для выхода:");
            email = scanner.nextLine();

            // Если пользователь ввел "exit", то мы выходим из цикла
            if (email.equalsIgnoreCase("exit")) {
                break;
            }

            // проверяем, соответствует ли введенная строка регулярному выражению и выводим результат
            boolean isValid = Pattern.matches(regex, email);
            System.out.println(email + ": " + isValid);
        }
    }
}