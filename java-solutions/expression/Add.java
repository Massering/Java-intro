package expression;

import java.math.BigInteger;

public class Add extends AddSubtract {
    public Add(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return false;
    }

    @Override
    protected char sign() {
        return PLUS;
    }

    //:NOTE: не вынесен общий код
    @Override
    public int evaluate(int x) {
        return left.evaluate(x) + right.evaluate(x);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return left.evaluate(x).add(right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) + right.evaluate(x, y, z);
    }
}
