package expression;

import java.math.BigInteger;

public class Multiply extends MultDivide {
    public Multiply(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    public BigInteger evaluate(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public int evaluate(int left, int right) {
        return left * right;
    }

    @Override
    protected char sign() {
        return MULT;
    }

    @Override
    protected boolean rightNeedsBrackets() {
        if (right instanceof Operation rightOp) {
            char oSign = rightOp.sign();
            return oSign == PLUS || oSign == MINUS || oSign == DIV;
        }
        return false;
    }
}
