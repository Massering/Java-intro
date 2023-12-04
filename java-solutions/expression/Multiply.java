package expression;

import java.math.BigInteger;

public class Multiply extends MultDivide {
    public Multiply(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected boolean rightNeedsBrackets() {
        if (right instanceof Operation rightOp) {
            char oSign = rightOp.sign();
            return oSign == PLUS || oSign == MINUS || oSign == DIV;
        }
        return false;
    }

    @Override
    protected char sign() {
        return MULT;
    }

    @Override
    public int evaluate(int x) {
        return left.evaluate(x) * right.evaluate(x);
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return left.evaluate(x).multiply(right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return left.evaluate(x, y, z) * right.evaluate(x, y, z);
    }
}
