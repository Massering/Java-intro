package expression;

import java.util.Objects;

public abstract class Operation implements Expression {
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
        if (needBrackets(left, this)) {
            sb.append('(').append(left.toMiniString()).append(')');
        } else {
            sb.append(left.toMiniString());
        }
        sb.append(' ').append(sign()).append(' ');
        if (needBrackets(right, this)) {
            sb.append('(').append(right.toMiniString()).append(')');
        } else {
            sb.append(right.toMiniString());
        }
        return sb.toString();
    }

    private boolean needBrackets(Expression left, Expression right) {
        return left instanceof Divide && right instanceof Multiply ||
                left instanceof AddSubtract && right instanceof MultDivide;
    }
}
