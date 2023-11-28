
//7. Написать регулярное выражение, определяющее является ли данная строчка валидным E-mail адресом согласно RFC под номером 2822.
//   – пример правильных выражений: user@example.com, root@localhost
//   – пример неправильных выражений: bug@@@com.ru, @val.ru, Just Text2.
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        String[] testStrings = {
                "user@example.com",
                "root@localhost",
                "bug@@@com.ru",
                "@val.ru",
                "Just Text2"
        };

        for (String s : testStrings) {
            boolean isValid = Pattern.matches(regex, s);
            System.out.println(s + ": " + isValid);
        }
    }
}