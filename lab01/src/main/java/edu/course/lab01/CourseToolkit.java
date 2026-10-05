package edu.course.lab01;

/**
 * Небольшие методы для первой лабораторной работы.
 */
public final class CourseToolkit {

    private CourseToolkit() {
        // Утилитарный класс не должен иметь экземпляров.
    }

    /**
     * Возвращает true, если число четное.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int div = 3; div * div <= number; div += 2) {
            if (number % div == 0) {
                return false;
            }
    }
    return true;

}
    public static boolean isPalindrome(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }
        String reversed = new StringBuilder(text).reverse().toString();
        return text.equals(reversed);
    }

    public static double average(int[] values) {
        if (values == null) {
            throw new IllegalArgumentException("array must not be null");
        }
        if (values.length == 0) {
            throw new IllegalArgumentException("array must not be empty");
        }
        int sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum = sum + values[i];
        }
        return (double) sum / values.length;
    }
}
