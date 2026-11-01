package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceCalculatorTest {
    @Test
    void calculatesPriceWithoutDiscount() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.calculate(100.0), 0.001);
    }

    @Test
    void calculatesPriceWithPercentDiscount() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(20.0));
        assertEquals(80.0, calculator.calculate(100.0), 0.001);
    }

    @Test
    void throwsForInvalidPercent() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(101.0));
    }

    @Test
    void throwsForNegativePrice() {
        PriceCalculator calculator = new PriceCalculator(new PercentDiscount(10.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(-50.0));
    }

    @Test
    void calculatesPriceWithDifferentPolicies() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.calculate(100.0), 0.001); // Без скидки

        calculator = new PriceCalculator(new PercentDiscount(20.0));
        assertEquals(80.0, calculator.calculate(100.0), 0.001);  // Со скидкой
    }
}