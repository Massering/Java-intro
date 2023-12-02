package expression;

public class Divide extends MultDivide {
    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    char sign() {
        return DIV;
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) / right.evaluate(x);
    }
}
