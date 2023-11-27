package expression;

public class Subtract extends AddSubtract {
    Subtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    char sign() {
        return '-';
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) - right.evaluate(x);
    }
}
