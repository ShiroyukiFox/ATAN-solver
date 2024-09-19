import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите число для вычисления (atan)");

        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        double x = 0;
        try {
            x = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Пожалуйста, введите число");
            System.out.println();
            main(args);
        }

        double result = calculate(x);

        System.out.println("atan(x) по ряду Тейлора = " + result);
        System.out.println("atan(x) по библиотеке Math = " + Math.atan(x));
        System.out.println();
        main(args);
    }

    public static double calculate(double x) {
        int n = 2; // Начинаем со второго члена ряда, так как первый изначально записан в tempSum (формула начинается с x)
        double tempSum = x; // Текущая сумма ряда
        double prevSum = 0.0; // Предыдущая сумма ряда
        double e = 1.0E-6; // Допустимая ошибка

        if (x > 1) {
            return Math.PI / 2 - calculate(1 / x);
        } else if (x < -1) {
            return -Math.PI / 2 - calculate(1 / x);
        }

        while (Math.abs(tempSum - prevSum) > e) { // Пока разность больше ошибки, цикл будет продолжаться
            prevSum = tempSum;
            tempSum = tempSum + Math.pow(-1, n - 1) * (Math.pow(x, 2 * n - 1) / (2 * n - 1));
            n++;
        }

        return tempSum;
    }
}