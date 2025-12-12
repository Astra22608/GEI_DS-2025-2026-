package test;

import main.e2.expressions.*;
import main.e2.operators.BinaryOperator;
import main.e2.operators.UnaryOperator;
import main.e2.visitors.*;
import main.e2.expressions.BinaryOperation;
import main.e2.expressions.Constant;
import main.e2.expressions.Expression;
import main.e2.expressions.UnaryOperation;
import main.e2.visitors.EvaluationVisitor;
import main.e2.visitors.PostfixVisitor;
import main.e2.visitors.PrefixVisitor;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ejercicio 2 - Expresiones aritméticas (Composite + Visitor)")
class ExpressionTest {

    @Test
    @DisplayName("2 + (5 x √9) → 17.0 y postfija correcta del PDF")
    void testEjemploEnunciadoSimple() {
        Expression expr = new BinaryOperation(
                new Constant(2),
                new BinaryOperation(
                        new Constant(5),
                        new UnaryOperation(new Constant(9), UnaryOperator.SQRT),
                        BinaryOperator.MULTIPLY
                ),
                BinaryOperator.ADD
        );

        EvaluationVisitor eval = new EvaluationVisitor();
        expr.accept(eval);
        assertEquals(17.0, eval.getResult(), 0.0001);

        PostfixVisitor post = new PostfixVisitor();
        expr.accept(post);
        assertEquals("2 5 9 SQRT x +", post.getPostfix());
    }

    @Test
    @DisplayName("(-5) / (4 + √2) → ≈ -1.381 y postfija correcta (con unary +/-)")
    void testEjemploComplejoPDF() {
        Expression expr = new BinaryOperation(
                new UnaryOperation(new Constant(5), UnaryOperator.NEGATE),
                new BinaryOperation(
                        new Constant(4),
                        new UnaryOperation(new Constant(2), UnaryOperator.SQRT),
                        BinaryOperator.ADD
                ),
                BinaryOperator.DIVIDE
        );

        EvaluationVisitor ev = new EvaluationVisitor();
        expr.accept(ev);
        assertEquals(-5.0 / (4.0 + Math.sqrt(2.0)), ev.getResult(), 0.0001);

        PostfixVisitor pv = new PostfixVisitor();
        expr.accept(pv);
        assertEquals("5 +/- 4 2 SQRT + /", pv.getPostfix());
    }

    @Test
    @DisplayName("Extensibilidad → operador unario SIN y COS")
    void testNuevoOperadorUnarioSinCos() {
        Expression expr = new UnaryOperation(
                new UnaryOperation(new Constant(0), UnaryOperator.SIN),
                UnaryOperator.COS
        );

        EvaluationVisitor ev = new EvaluationVisitor();
        expr.accept(ev);
        assertEquals(Math.cos(Math.sin(0)), ev.getResult(), 0.0001); // 1.0
    }

    @Test
    @DisplayName("Extensibilidad → operador binario módulo %")
    void testNuevoOperadorBinarioModulo() {
        Expression expr = new BinaryOperation(
                new Constant(17),
                new Constant(5),
                BinaryOperator.MODULO
        );

        EvaluationVisitor ev = new EvaluationVisitor();
        expr.accept(ev);
        assertEquals(2.0, ev.getResult(), 0.0001);
    }

    @Test
    @DisplayName("Notación prefija (extensión pedida)")
    void testNotacionPrefija() {
        Expression expr = new BinaryOperation(
                new Constant(2),
                new BinaryOperation(new Constant(5), new Constant(3), BinaryOperator.MULTIPLY),
                BinaryOperator.ADD
        );

        PrefixVisitor prefix = new PrefixVisitor();
        expr.accept(prefix);
        assertEquals("+ 2 x 5 3", prefix.getPrefix());
    }

    @Test
    @DisplayName("Errores esperados → división por cero")
    void testDivisionPorCero() {
        Expression expr = new BinaryOperation(
                new Constant(10),
                new Constant(0),
                BinaryOperator.DIVIDE
        );

        EvaluationVisitor ev = new EvaluationVisitor();
        assertThrows(ArithmeticException.class, () -> expr.accept(ev));
    }

    @Test
    @DisplayName("Errores esperados → raíz cuadrada de negativo")
    void testRaizNegativo() {
        Expression expr = new UnaryOperation(new Constant(-4), UnaryOperator.SQRT);

        EvaluationVisitor ev = new EvaluationVisitor();
        assertThrows(ArithmeticException.class, () -> expr.accept(ev));
    }

    @Test
    @DisplayName("Formato exacto de números en postfija (sin .0 innecesarios cuando son enteros)")
    void testFormatoPostfijaSinDecimalesInnecesarios() {
        Expression expr = new BinaryOperation(new Constant(6), new Constant(2), BinaryOperator.DIVIDE);

        PostfixVisitor pv = new PostfixVisitor();
        expr.accept(pv);
        assertEquals("6 2 /", pv.getPostfix());
    }
}