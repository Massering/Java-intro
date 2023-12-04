package expression;

public abstract class MultDivide extends Operation {
    MultDivide(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected boolean leftNeedsBrackets() {
        return left instanceof AddSubtract;
    }
}
