package expression;

public class Add extends AddSubtract {
    Add(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    char sign() {
        return PLUS;
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }
}
