package expression;

import java.math.BigInteger;

public class Subtract extends AddSubtract {
    public Subtract(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    public BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public int evaluate(int left, int right) {
        return left - right;
    }

    @Override
    protected char sign() {
        return MINUS;
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return right instanceof AddSubtract;
    }
}
