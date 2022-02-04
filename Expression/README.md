# Домашнее задание 11. Выражения
1. Разработайте классы Const, Variable, Add, Subtract, Multiply, Divide для вычисления выражений с одной переменной в типе int (интерфейс Expression).

2. Классы должны позволять составлять выражения такого вида. При вычислении такого выражения вместо каждой переменной подставляется значение, переданное в качестве параметра методу evaluate. Таким образом, результатом вычисления приведенного примера должно стать число `7`.
```
new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
).evaluate(5)
```

3. Метод toString должен выдавать запись выражения в полноскобочной форме. \
Например код ниже должен выдавать `((2 * x) - 3)`.
```
new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
).toString()
```         

4. Сложный вариант. Метод toMiniString (интерфейс ToMiniString) должен выдавать выражение с минимальным числом скобок. Например код ниже должен выдавать `2 * x - 3`.
```
new Subtract(
    new Multiply(
        new Const(2),
        new Variable("x")
    ),
    new Const(3)
).toMiniString()
```         

5. Реализуйте метод equals, проверяющий, что два выражения совпадают. Например, первое выражение должно выдавать `true`, а второе - `false`.
```
new Multiply(new Const(2), new Variable("x"))
    .equals(new Multiply(new Const(2), new Variable("x")))
```         

```
new Multiply(new Const(2), new Variable("x"))
    .equals(new Multiply(new Variable("x"), new Const(2)))
```            
6. Для тестирования программы должен быть создан класс Main, который вычисляет значение выражения `x^2−2x+1`, для `x`, заданного в командной строке.
7. При выполнении задания следует обратить внимание на:
    - Выделение общего интерфейса создаваемых классов.
    - Выделение абстрактного базового класса для бинарных операций.

Модификации:
 * *Базовая*
    * Реализуйте интерфейс [Expression](Expression/expression/Expression.java)
 * *DoubleTriple* (38, 39)
    * Дополнительно реализуйте интерфейсы 
      [DoubleExpression](Expression/expression/DoubleExpression.java) и
      [TripleExpression](Expression/expression/TripleExpression.java)
