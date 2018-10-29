package ex01;

public class Not implements BooleanExpression {
	private BooleanExpression _op;
	
	public Not(BooleanExpression op) {
		_op = op;
	}
	
	public BooleanExpression getOp() {
		return _op;
	}
}
