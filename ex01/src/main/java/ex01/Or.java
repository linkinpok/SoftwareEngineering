package ex01;

public class Or implements BooleanExpression {
	private BooleanExpression _op1, _op2;
	
	public Or(BooleanExpression op1, BooleanExpression op2) {
		_op1 = op1;
		_op2 = op2;
	}
	
	public BooleanExpression getLeftOp() {
		return _op1;
	}
	
	public BooleanExpression getRightOp() {
		return _op2;
	}
}
