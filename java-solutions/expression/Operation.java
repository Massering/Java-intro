package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class Operation implements MyExpression {
    static protected final char PLUS = '+';
    static protected final char MINUS = '-';
    static protected final char MULT = '*';
    static protected final char DIV = '/';
    public final MyExpression left;
    public final MyExpression right;

    Operation(MyExpression left, MyExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(int x) {
        return evaluate(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return evaluate(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    protected abstract int evaluate(int left, int right);

    protected abstract BigInteger evaluate(BigInteger left, BigInteger right);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(left, operation.left) && Objects.equals(right, operation.right);
    }

    @Override
    public int hashCode() {
        return ((sign() * 89 + left.hashCode()) * 19 + right.hashCode()) * 7 % 2147483647;
    }

    protected abstract char sign();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(left).append(' ').append(sign()).append(' ').append(right).append(')');
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (leftNeedsBrackets()) {
            sb.append('(').append(left.toMiniString()).append(')');
        } else {
            sb.append(left.toMiniString());
        }
        sb.append(' ').append(sign()).append(' ');
        if (rightNeedsBrackets()) {
            sb.append('(').append(right.toMiniString()).append(')');
        } else {
            sb.append(right.toMiniString());
        }
        return sb.toString();
    }

    protected abstract boolean leftNeedsBrackets();

    protected abstract boolean rightNeedsBrackets();
}
