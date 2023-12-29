import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Что желаете запустить (S)Сервер или (C)Клиент?");
        // Get user choice
        String choice = scanner.nextLine();

        // Check user choice and start server or client accordingly
        if (choice.equalsIgnoreCase("S")) {
            // Start a new instance of ChatServer
            new ChatServer().start();
        } else if (choice.equalsIgnoreCase("C")) {
            // Start a new instance of ChatClient
            new ChatClient().start();
        } else {
            System.out.println("Я вас не понимать :(");
        }

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}