package expression;

import java.util.Objects;

public abstract class AbstractBinaryOperator implements CommonExpression {
    protected final CommonExpression left, right;

    public AbstractBinaryOperator(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    protected abstract String getView();

    protected abstract int Calculate(int left, int right);

    protected abstract double Calculate(double left, double right);


    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            final AbstractBinaryOperator that = (AbstractBinaryOperator) obj;
            return that.left.equals(left) && that.right.equals(right);
        }
        return false;
    }

    @Override
    final public int hashCode() {
        return Objects.hash(left, right, getClass());
    }

    @Override
    public int evaluate(int x) {
        return Calculate(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return Calculate(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return Calculate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + left + " " + getView() + " " + right + ")";
    }

    private boolean rightRequireBrackets() {
        return right.getPriority() < getPriority() ||
                (right.getPriority() == getPriority() && (right.orderRequired() || orderRequired()));
    }

    @Override
    public String toMiniString() {
        StringBuilder res = new StringBuilder();
        processBrackets(res, left, left.getPriority() < getPriority());
        res.append(" ").append(getView()).append(" ");
        processBrackets(res, right, rightRequireBrackets());
        return res.toString();
    }

    public void processBrackets(StringBuilder stringBuilder, CommonExpression expression, boolean brackets) {
        if (brackets) {
            stringBuilder.append("(").append(expression.toMiniString()).append(")");
        } else {
            stringBuilder.append(expression.toMiniString());
        }
    }

}
