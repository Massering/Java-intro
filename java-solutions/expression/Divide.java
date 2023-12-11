package expression;

import java.math.BigInteger;

public class Divide extends MultDivide {
    public Divide(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    public BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.divide(right);
    }

    @Override
    public int evaluate(int left, int right) {
        return left / right;
    }

    @Override
    protected char sign() {
        return DIV;
    }

    @Override
    protected boolean rightNeedsBrackets() {
        return right instanceof Operation;
    }
}
