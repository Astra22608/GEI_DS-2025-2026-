package main.e2.expressions;

import main.e2.operators.UnaryOperator;
import main.e2.visitors.ExpressionVisitor;

public class UnaryOperation extends Expression {
    private final Expression operand;
    private final UnaryOperator operator;

    public UnaryOperation(Expression operand, UnaryOperator operator) {
        this.operand = operand;
        this.operator = operator;
    }

    public Expression getOperand() { return operand; }
    public UnaryOperator getOperator() { return operator; }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
