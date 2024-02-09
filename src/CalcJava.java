import java.util.Scanner;

public class CalcJava {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа, римские или арабские через пробел.");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }
    public static String parse(String expression) throws Exception {
        String operation;
        String result;
        int number1;
        int number2;
        boolean romanNumb;
        String[] meaning = expression.split(" ");
        if (meaning.length != 3) throw new Exception("Должно быть три значения(число, знак, число)");

        operation = detectOperation(meaning[1]);
        if (operation == null) throw new Exception("Неподдерживаемая математическая операция");

        if (Roman.isRoman(meaning[0]) && Roman.isRoman(meaning[2])) {

            number1 = Roman.convertArab(meaning[0]);
            number2 = Roman.convertArab(meaning[2]);
            romanNumb = true;
        }

        else if (!Roman.isRoman(meaning[0]) && !Roman.isRoman(meaning[2])) {
            try {
                number1 = Integer.parseInt(meaning[0]);
                number2 = Integer.parseInt(meaning[2]);
                romanNumb = false;
            } catch (NumberFormatException e) {
                throw new Exception("Можно использовать только целые числа");
            }
        }

        else {
            throw new Exception("Числа должны быть в одном формате, например V + II");
        }
        if (number1 > 10 || number2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(number1, number2, operation);
        if (romanNumb) {

            if (arabian <= 0) {
                throw new Exception("Римское число не может быть меньше нуля");
            }

            result = Roman.convertRom(arabian);
        } else {

            result = String.valueOf(arabian);
        }

        return result;
    }
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int calc(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}
class Roman {
    static String[] romanNumeral = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};
    static boolean isRoman(String val) {
        for (int i = 0; i < romanNumeral.length; i++) {
            if (val.equals(romanNumeral[i])) {
                return true;
            }
        }
        return false;
    }
    static int convertArab(String roman) {
        for (int i = 0; i < romanNumeral.length; i++) {
            if (roman.equals(romanNumeral[i])) {
                return i;
            }
        }
        return -1;
    }
    static String convertRom(int arabian) {
        return romanNumeral[arabian];
    }
}