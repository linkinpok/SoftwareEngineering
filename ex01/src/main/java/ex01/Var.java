package ex01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Var implements BooleanExpression {
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
		boolean b = map.get(_var);
		return b;
	}

	@Override
	public List<BooleanExpression> disjunctiveTerms() {
		List<BooleanExpression> list = Arrays.asList(this);
		return list;
	}

	@Override
	public BooleanExpression toDNF() {
		return this;
	}
}
