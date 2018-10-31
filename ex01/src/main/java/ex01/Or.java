package ex01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Or implements BooleanExpression {
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

	@Override
	public String toPostfixString() {
		return _op1.toPostfixString() + " " + _op2.toPostfixString() + " |";
	}

	@Override
	public boolean evaluate(Map<String, Boolean> map) {
		boolean b1 = map.get(_op1.toPostfixString());
		boolean b2 = map.get(_op2.toPostfixString());
		return b1 | b2;
	}

	@Override
	public List<BooleanExpression> disjunctiveTerms() {
		List<BooleanExpression> list = Arrays.asList(_op1, _op2);
		return list;
	}

	@Override
	public BooleanExpression toDNF() {
		return new Or(_op1.toDNF(), _op2.toDNF());
	}
}
