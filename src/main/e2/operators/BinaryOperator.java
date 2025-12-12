package main.e2.operators;

import java.util.function.DoubleBinaryOperator;

public enum BinaryOperator {
    ADD("+", Double::sum),
    SUBTRACT("-", (a, b) -> a - b),
    MULTIPLY("x", (a, b) -> a * b),
    DIVIDE("/",   (a, b) -> { if (b == 0) throw new ArithmeticException("División por cero"); return a / b; }),
    MODULO("%",   (a, b) -> a % b);        // extensión pedida

    private final String symbol;
    private final DoubleBinaryOperator function;

    BinaryOperator(String symbol, DoubleBinaryOperator function) {
        this.symbol = symbol;
        this.function = function;
    }

    public double apply(double left, double right) {
        return function.applyAsDouble(left, right);
    }

    public String getSymbol() { return symbol; }
}