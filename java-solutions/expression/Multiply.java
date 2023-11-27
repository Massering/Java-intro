package expression;

public class Multiply extends MultDivide {
    public Multiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    char sign() {
        return '*';
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) * right.evaluate(x);
    }
}
