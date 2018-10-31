package ex01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class Not implements BooleanExpression {
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
		boolean b = map.get(_op.toPostfixString());
		return !b;
	}

	@Override
	public List<BooleanExpression> disjunctiveTerms() {
		List<BooleanExpression> list = Arrays.asList(this);
		return list;
	}

	@Override
	public BooleanExpression toDNF() {
		if (_op instanceof Var) {
			return this;
		} else if (_op instanceof Not) {
			return ((Not) _op).getOp().toDNF();
		} else if (_op instanceof And) { // _op = A&B
			BooleanExpression op1 = ((And) _op).getLeftOp(); // A
			BooleanExpression op2 = ((And) _op).getRightOp(); // B
			BooleanExpression op1_not = new Not(op1); // !A
			BooleanExpression op2_not = new Not(op2); // !B
			return new Or(op1_not.toDNF(), op2_not.toDNF()); // DNF(!A)+DNF(!B)
		} else { // if (_op instanceof Or) // _op = A|B
			BooleanExpression op1 = ((Or) _op).getLeftOp(); // A
			BooleanExpression op2 = ((Or) _op).getRightOp(); // B
			BooleanExpression op1_not = new Not(op1); // !A
			BooleanExpression op2_not = new Not(op2); // !B
			return (new And(op1_not, op2_not)).toDNF(); // DNF(!A&!B)
		}
	}
}
