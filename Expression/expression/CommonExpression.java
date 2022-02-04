package expression;

public interface CommonExpression extends Expression, TripleExpression, DoubleExpression {
    int getPriority();
    boolean orderRequired();
}
