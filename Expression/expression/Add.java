package expression;

public class Add extends AbstractBinaryOperator {

    public Add(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "+";
    }

    @Override
    protected int Calculate(int left, int right) {
        return left + right;
    }

    @Override
    protected double Calculate(double left, double right) {
        return left + right;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean orderRequired() {
        return false;
    }
}
