package ex01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class And implements BooleanExpression {
	private BooleanExpression _op1, _op2;

	public And(BooleanExpression op1, BooleanExpression op2) {
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
		return _op1.toPostfixString() + " " + _op2.toPostfixString() + " &";
	}

	@Override
	public boolean evaluate(Map<String, Boolean> map) {
		boolean b1 = map.get(_op1.toPostfixString());
		boolean b2 = map.get(_op2.toPostfixString());
		return b1 & b2;
	}

	@Override
	public List<BooleanExpression> disjunctiveTerms() {
		List<BooleanExpression> list = Arrays.asList(this);
		return list;
	}

	@Override
	public BooleanExpression toDNF() {
		BooleanExpression op1_dnf = _op1.toDNF();
		BooleanExpression op2_dnf = _op2.toDNF();
		if (!(op1_dnf instanceof Or) && !(op2_dnf instanceof Or)) { // neither DNF(op1) nor DNF(op2) is Or object
			return new And(op1_dnf, op2_dnf);
		} else {
			List<BooleanExpression> list1 = op1_dnf.disjunctiveTerms();
			List<BooleanExpression> list2 = op2_dnf.disjunctiveTerms();
			List<BooleanExpression> list_all = new ArrayList<>();
			for (BooleanExpression expr1 : list1) {
				for (BooleanExpression expr2 : list2) {
					list_all.add(new And(expr1, expr2));
				}
			}
			// System.out.println(list_all);
			BooleanExpression result;
			if (list_all.size() == 1)
				result = list_all.get(0).toDNF();
			else
				result = new Or(list_all.get(0).toDNF(), list_all.get(1).toDNF());
			for (int i = 2; i < list_all.size(); i++) {
				result = new Or(result, list_all.get(i).toDNF());
			}
			return result;
		}
	}
}
