package expression;

import java.util.Objects;

public abstract class Operation implements Expression, ToMiniString {
    static protected final char PLUS = '+';
    static protected final char MINUS = '-';
    static protected final char MULT = '*';
    static protected final char DIV = '/';
    public final Expression left;
    public final Expression right;

    Operation(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(left, operation.left) && Objects.equals(right, operation.right);
    }

    @Override
    public int hashCode() {
        return ((Objects.hash(sign()) * 17 + Objects.hash(left)) * 17 + Objects.hash(right)) % 2147483647;
    }

    abstract char sign();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('(').append(left).append(' ').append(sign()).append(' ').append(right).append(')');
        return sb.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder sb = new StringBuilder();
        if (leftNeedsBrackets(left, this)) {
            sb.append('(').append(left.toMiniString()).append(')');
        } else {
            sb.append(left.toMiniString());
        }
        sb.append(' ').append(sign()).append(' ');
        if (rightNeedsBrackets(right, this)) {
            sb.append('(').append(right.toMiniString()).append(')');
        } else {
            sb.append(right.toMiniString());
        }
        return sb.toString();
    }

    private boolean leftNeedsBrackets(Expression left, Operation main) {
        if (left instanceof Operation leftOp) {
            char sign = main.sign();
            char oSign = leftOp.sign();
            return (oSign == PLUS || oSign == MINUS) && (sign == DIV || sign == MULT);
        }
        return false;
    }

    private boolean rightNeedsBrackets(Expression right, Operation main) {
        if (right instanceof Operation rightOp) {
            char sign = main.sign();
            char oSign = rightOp.sign();
            return (oSign == PLUS || oSign == MINUS || oSign == DIV) && (sign == DIV || sign == MULT) ||
                    (oSign == DIV || oSign == MULT) && sign == DIV ||
                    (oSign == PLUS || oSign == MINUS) && sign == MINUS;
        }
        return false;
    }
}
