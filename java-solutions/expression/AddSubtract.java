package expression;

public abstract class AddSubtract extends Operation {
    AddSubtract(MyExpression left, MyExpression right) {
        super(left, right);
    }

    @Override
    protected boolean leftNeedsBrackets() {
        return false;
    }
}
