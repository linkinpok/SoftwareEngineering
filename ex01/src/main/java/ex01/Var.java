package ex01;

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
}
