//4. Создать стек из элементов каталога.

import java.io.File;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Создаем стек
        Stack<String> directoryStack = new Stack<>();

        // Путь к каталогу
        String directoryPath = System.getProperty("user.home");

        // Создаем объект File
        File directory = new File(directoryPath);

        // Получаем список файлов и каталогов
        File[] files = directory.listFiles();

        // Добавляем их в стек
        for (File file : files) {
            directoryStack.push(file.getName());
        }

        // Выводим стек
        while (!directoryStack.isEmpty()) {
            System.out.println(directoryStack.pop());
        }
    }
}