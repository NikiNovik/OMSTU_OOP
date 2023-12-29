import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    // Start the client
    public void start() {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            // Получаем никнейм пользователя
            System.out.print("Введите никнейм: ");
            String name = scanner.nextLine();
            writer.println(name);

            // Запускаем поток для чтения сообщений с сервера
            Thread readerThread = new Thread(() -> readMessages(reader));
            readerThread.start();

            while (true) {
                // Отправляем сообщение пользователя на сервер (да, тут специльно идет подпись отправителя)
                System.out.print("[" + name + "]: ");
                String message = scanner.nextLine();
                writer.println("[" + name + "]: " + message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Чтение сообщений от сервера
    private void readMessages(BufferedReader reader) {
        try {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}