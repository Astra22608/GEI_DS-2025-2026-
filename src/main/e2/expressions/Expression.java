package main.e2.expressions;

import main.e2.visitors.ExpressionVisitor;

public abstract class Expression {
    public abstract void accept(ExpressionVisitor visitor);
}
