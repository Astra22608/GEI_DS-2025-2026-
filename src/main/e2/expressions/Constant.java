package main.e2.expressions;

import main.e2.visitors.ExpressionVisitor;

public class Constant extends Expression {
    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
