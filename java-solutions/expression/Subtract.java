package expression;

import java.math.BigInteger;

public class Subtract extends AddSubtract {
    public Subtract(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return right instanceof AddSubtract;
    }

    @Override
    protected char sign() {
        return MINUS;
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) - right.evaluate(x);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return left.evaluate(x).subtract(right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) - right.evaluate(x, y, z);
    }
}
