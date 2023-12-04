package expression;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        final Variable vx = new Variable("x");
        final Const c2 = new Const(BigInteger.TWO);
        MyExpression expr = new Add(vx, c2);

        System.out.println(expr);
        System.out.println(expr.toMiniString());
        System.out.println(expr.evaluate(BigInteger.valueOf(Integer.MAX_VALUE).multiply(BigInteger.valueOf(10000))));
    }
}
