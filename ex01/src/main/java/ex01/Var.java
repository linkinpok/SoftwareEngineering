package ex01;

import java.util.Map;

class Var implements BooleanExpression {
	private String _var;

	public Var(String var) {
		_var = var;
	}

	public String getName() {
		return _var;
	}
	
	@Override
	public String toPostfixString() {		
		return _var;
	}
	
	@Override
	public boolean evaluate(Map<String, Boolean> map) {
		// TODO Auto-generated method stub
		return false;
	}
}
