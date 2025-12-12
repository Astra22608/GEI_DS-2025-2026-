package main.e2.operators;

import java.util.function.DoubleUnaryOperator;

public enum UnaryOperator {
    SQRT("SQRT",  Math::sqrt),
    NEGATE("+/-", x -> -x),
    SIN("SIN",    Math::sin),
    COS("COS",    Math::cos),
    TAN("TAN",    Math::tan);          // extensión fácil

    private final String symbol;
    private final DoubleUnaryOperator function;

    UnaryOperator(String symbol, DoubleUnaryOperator function) {
        this.symbol = symbol;
        this.function = function;
    }

    public double apply(double value) {
        if (this == SQRT && value < 0)
            throw new ArithmeticException("Raíz de número negativo");
        return function.applyAsDouble(value);
    }

    public String getSymbol() { return symbol; }
}