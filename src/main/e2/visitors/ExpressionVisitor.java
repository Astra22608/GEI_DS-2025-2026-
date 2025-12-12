package main.e2.visitors;

import main.e2.expressions.BinaryOperation;
import main.e2.expressions.Constant;
import main.e2.expressions.UnaryOperation;

public interface ExpressionVisitor {
    void visit(Constant constant);
    void visit(UnaryOperation unary);
    void visit(BinaryOperation binary);
}