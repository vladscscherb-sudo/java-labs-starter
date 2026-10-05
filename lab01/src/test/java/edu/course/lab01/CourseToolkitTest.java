package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseToolkitTest {

    @Test
    void returnsTrueForEvenNumber() {
        boolean result = CourseToolkit.isEven(8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForOddNumber() {
        boolean result = CourseToolkit.isEven(7);

        assertFalse(result);
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        boolean result = CourseToolkit.isEven(-8);

        assertTrue(result);
    }

    @Test
    void returnsFalseForNumberLessThanTwo() {
        boolean result = CourseToolkit.isPrime(-1);

        assertFalse(result);
    }

    @Test
    void returnsTrueForTwo() {
        boolean result = CourseToolkit.isPrime(2);

        assertTrue(result);
    }

    @Test
    void returnsFalseForCompositeNumber() {
        boolean result = CourseToolkit.isPrime(8);

        assertFalse(result);
    }

    @Test
    void returnsTrueForPrimeNumber() {
        boolean result = CourseToolkit.isPrime(7);

        assertTrue(result);
    }

    @Test
    void returnsFalseForSquareOfPrime() {
        boolean result = CourseToolkit.isPrime(25);

        assertFalse(result);
    }

    @Test
    void returnsTrueForPalindrome() {
        boolean result = CourseToolkit.isPalindrome("tenet");

        assertTrue(result);
    }

    @Test
    void returnsFalseForNonPalindrome() {
        boolean result = CourseToolkit.isPalindrome("world");

        assertFalse(result);
    }

    @Test
    void returnsFalseForDifferentCase() {
        boolean result = CourseToolkit.isPalindrome("Tenet");

        assertFalse(result);
    }

    @Test
    void returnsMeanOfPositiveNumbers() {
        double result = CourseToolkit.average(new int[]{1, 2, 3, 4, 5});

        assertEquals(3, result, 1e-9);
    }

    @Test
    void returnsMeanOfNegativeNumbers() {
        double result = CourseToolkit.average(new int[]{-1, -2, -3, -4, -5});

        assertEquals(-3, result, 1e-9);
    }

    @Test
    void throwsForEmptyArray() {
        assertThrows(IllegalArgumentException.class, () -> CourseToolkit.average(new int[]{}));
    }
}
