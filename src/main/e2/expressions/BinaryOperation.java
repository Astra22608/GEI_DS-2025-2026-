package main.e2.expressions;

import main.e2.operators.BinaryOperator;
import main.e2.visitors.ExpressionVisitor;

public class BinaryOperation extends Expression {
    private final Expression left;
    private final Expression right;
    private final BinaryOperator operator;

    public BinaryOperation(Expression left, Expression right, BinaryOperator operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    public Expression getLeft()   { return left; }
    public Expression getRight()  { return right; }
    public BinaryOperator getOperator() { return operator; }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
