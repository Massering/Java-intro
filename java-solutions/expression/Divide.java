package expression;

import java.math.BigInteger;

public class Divide extends MultDivide {
    public Divide(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return right instanceof Operation;
    }

    @Override
    protected char sign() {
        return DIV;
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) / right.evaluate(x);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return left.evaluate(x).divide(right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) / right.evaluate(x, y, z);
    }
}
