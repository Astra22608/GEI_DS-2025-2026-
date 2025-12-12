package main.e2.visitors;

import main.e2.expressions.BinaryOperation;
import main.e2.expressions.Constant;
import main.e2.expressions.UnaryOperation;

public class EvaluationVisitor implements ExpressionVisitor {
    private double result;

    @Override
    public void visit(Constant constant) {
        result = constant.getValue();
    }

    @Override
    public void visit(UnaryOperation unary) {
        unary.getOperand().accept(this);
        result = unary.getOperator().apply(result);
    }

    @Override
    public void visit(BinaryOperation binary) {
        binary.getLeft().accept(this);
        double leftValue = result;

        binary.getRight().accept(this);
        double rightValue = result;

        result = binary.getOperator().apply(leftValue, rightValue);
    }

    public double getResult() {
        return result;
    }
}