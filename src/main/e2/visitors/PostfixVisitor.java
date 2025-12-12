package main.e2.visitors;

import main.e2.expressions.BinaryOperation;
import main.e2.expressions.Constant;
import main.e2.expressions.UnaryOperation;
import java.util.Locale;

public class PostfixVisitor implements ExpressionVisitor {
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
        unary.getOperand().accept(this);
        // El símbolo del operador unario (como +/- o SQRT) va DETRÁS del número en postfija
        sb.append(unary.getOperator().getSymbol()).append(" ");
    }

    @Override
    public void visit(BinaryOperation binary) {
        binary.getLeft().accept(this);
        binary.getRight().accept(this);
        sb.append(binary.getOperator().getSymbol()).append(" ");
    }

    public String getPostfix() {
        return sb.toString().trim();
    }
}