package expression;

import java.math.BigInteger;

public class Add extends AddSubtract {
    public Add(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    public BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public int evaluate(int left, int right) {
        return left + right;
    }

    @Override
    protected char sign() {
        return PLUS;
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return false;
    }
}
