import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private List<PrintWriter> clients = new ArrayList<>();
    private static final String CLIENTS_FILE = "clients.txt";

    // Start the server
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Сервер запущен!");

            while (true) {
                // Принимаем подключение
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clients.add(writer);

                // Создаем поток для подключенного клиента
                Thread clientThread = new Thread(() -> handleClient(clientSocket, writer));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Отправка сообщения всем подключенным клиентам
    private void broadcastMessage(String message) {
        for (PrintWriter client : clients) {
            client.println(message);
        }
    }

    // Сохраняем список клиентов в файл
    private void saveClientToFile(String clientName) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(CLIENTS_FILE, true))) {
            fileWriter.write(clientName);
            fileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Взаимодействие с подключенными клиентами
    private void handleClient(Socket clientSocket, PrintWriter writer) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String clientName = reader.readLine();
            System.out.println("Новый пользователь: " + clientName);
            saveClientToFile(clientName);

            while (true) {
                // Read messages from the client and broadcast them to others
                String message = reader.readLine();
                if (message == null) break;
                System.out.println("Получено: " + message);
                broadcastMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}