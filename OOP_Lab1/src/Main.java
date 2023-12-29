import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // Создаем объект класса Scanner для чтения ввода пользователя
        Scanner scanner = new Scanner(System.in);

        // Выводим сообщение, что нужно ввести текст
        System.out.println("Введите текст:");

        // Считываем введенный текст
        String text = scanner.nextLine();

        // Разбиваем текст на слова, приводя текст к нижнему регистру и разделяя по небуквенно-нецифровым символам
        String[] words = text.toLowerCase().split("[^\\p{L}\\p{Nd}]+");

        // Создаем объект класса TreeMap для подсчета слов
        Map<String, Integer> wordCount = new TreeMap<>();

        // Проходим по всем словам в тексте
        for (String word : words) {
            // Если слово уже есть в словаре, увеличиваем его количество на 1
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            }
            // Если слова еще нет в словаре, добавляем его с количеством 1
            else {
                wordCount.put(word, 1);
            }
        }

        // Сортируем слова по количеству и выводим их
        wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println("Слово '" + entry.getKey() + "' повторилось " + entry.getValue() + " раз(а)"));
    }
}