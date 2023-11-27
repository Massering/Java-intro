package expression;

public class Divide extends MultDivide {
    public Divide(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    char sign() {
        return '/';
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) / right.evaluate(x);
    }
}
