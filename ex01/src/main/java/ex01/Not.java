package ex01;

import java.util.Map;

public class Not implements BooleanExpression {
	private BooleanExpression _op;
	
	public Not(BooleanExpression op) {
		_op = op;
	}
	
	public BooleanExpression getOp() {
		return _op;
	}
	
	@Override
	public String toPostfixString() {		
		return _op.toPostfixString() + " !";
	}
	
	@Override
	public boolean evaluate(Map<String, Boolean> map) {
		// TODO Auto-generated method stub
		return false;
	}
}
