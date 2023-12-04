package expression;

import java.math.BigInteger;
import java.util.Objects;

public class Variable implements MyExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public BigInteger evaluate(BigInteger x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (name) {
            case ("x"): {
                return x;
            }
            case ("y"): {
                return y;
            }
            case ("z"): {
                return z;
            }
        }
        throw new IllegalStateException("invalid variable name");
    }
}
