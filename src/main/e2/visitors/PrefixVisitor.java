package main.e2.visitors;

import main.e2.expressions.BinaryOperation;
import main.e2.expressions.Constant;
import main.e2.expressions.UnaryOperation;
import java.util.Locale;

public class PrefixVisitor implements ExpressionVisitor {
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void visit(Constant constant) {
        double val = constant.getValue();
        if (val == (long) val) {
            sb.append((long) val).append(" ");  // 5 en vez de 5.0
        } else {
            sb.append(String.format(Locale.US, "%.1f", val)).append(" ");
        }
    }

    @Override
    public void visit(UnaryOperation unary) {
        sb.append(unary.getOperator().getSymbol()).append(" ");
        unary.getOperand().accept(this);
    }


    @Override
    public void visit(BinaryOperation binary) {
        sb.append(binary.getOperator().getSymbol()).append(" ");
        binary.getLeft().accept(this);
        binary.getRight().accept(this);
    }

    public String getPrefix() {
        return sb.toString().trim();
    }
}