package expression;

public class Const implements CommonExpression {
    private final Number val;

    public Const(int val) {
        this.val = val;
    }

    public Const(double val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            return val.equals(((Const) obj).val);
        }
        return false;
    }

    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean orderRequired() {
        return false;
    }

    @Override
    public int evaluate(int x) {
        return val.intValue();
    }

    @Override
    public double evaluate(double x) {
        return val.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return val.intValue();
    }

    @Override
    public int hashCode() {
        return val.intValue();
    }
}
