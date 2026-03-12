package com.calculadora;

public class Calculator {

    public double Add(double number1, double number2) {
        return number1 + number2;
    }

    public double subtract(double number1, double number2) {
        return number1 - number2;
    }

    public double multiply(double number1, double number2) {
        return number1 * number2;
    }

    public double divide(double number1, double number2) {
        if (number2 == 0) {

            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return number1 / number2;
    }

    public float add(int i, int i1) {
        return i + i1;
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    //potenciacao
    public double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate the square root of a negative number");
        }
        return Math.sqrt(number);
    }
}

