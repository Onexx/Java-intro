package expression;

public class Multiply extends AbstractBinaryOperator {
    public Multiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    protected String getView() {
        return "*";
    }

    @Override
    protected int Calculate(int left, int right) {
        return left * right;
    }

    @Override
    protected double Calculate(double left, double right) {
        return left * right;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public boolean orderRequired() {
        return false;
    }
}
