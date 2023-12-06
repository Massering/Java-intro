package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Const implements MyExpression {
    //:NOTE: достаточно хранить только одно значение
    private final BigInteger bigValue;
    private final int value;

    public Const(int value) {
        this.bigValue = BigInteger.valueOf(value);
        this.value = value;
    }

    public Const(BigInteger value) {
        this.bigValue = value;
        this.value = value.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Const aConst = (Const) o;
        return Objects.equals(bigValue, aConst.bigValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value * 17);
    }

    @Override
    public int evaluate(int x) {
        return value;
    }

    @Override
    public String toString() {
        return bigValue.toString();
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return bigValue;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return value;
    }
}
