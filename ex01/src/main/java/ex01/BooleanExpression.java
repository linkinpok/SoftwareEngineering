package ex01;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public interface BooleanExpression {

	static BooleanExpression parseExpression(String str) {
		Stack<BooleanExpression> stack = new Stack<>();
		BooleanExpression op, op1, op2;
		String var;

		int i = 0;
		while (i < str.length()) {
			switch (str.charAt(i)) {
			case ' ':
				i++;
				break;
			case '&':
				if (stack.size() >= 2) { // enough operands
					op2 = stack.pop();
					op1 = stack.pop();
					stack.push(new And(op1, op2));
					i++;
					break;
				} else
					throw new IllegalArgumentException();
			case '|':
				if (stack.size() >= 2) { // enough operands
					op2 = stack.pop();
					op1 = stack.pop();
					stack.push(new Or(op1, op2));
					i++;
					break;
				} else
					throw new IllegalArgumentException();
			case '!':
				if (stack.size() >= 1) { // enough operand
					op = stack.pop();
					stack.push(new Not(op));
					i++;
					break;
				} else
					throw new IllegalArgumentException();
			default:
				if (str.indexOf(' ', i) < 0) { // str is a variable
					stack.push(new Var(str));
					i = str.length();
					break;
				} else {
					var = str.substring(i, str.indexOf(' ', i));
					stack.push(new Var(var));
					i += var.length();
					break;
				}
			}
		}

		if (stack.size() != 1) {
			throw new IllegalArgumentException();
		}
		return stack.pop();
	}

	String toPostfixString();

	boolean evaluate(Map<String, Boolean> map);

	default List<BooleanExpression> disjunctiveTerms() {
		return null;
	}

	BooleanExpression toDNF();

	/* TEST */
	default String toStringDNF() {
		if (this instanceof And) {
			return ((And) this).getLeftOp().toStringDNF() + " & " + ((And) this).getRightOp().toStringDNF();
		} else if (this instanceof Or) {
			return ((Or) this).getLeftOp().toStringDNF() + " | " + ((Or) this).getRightOp().toStringDNF();
		} else if (this instanceof Not) {
			return "!" + ((Not) this).getOp().toStringDNF();
		} else { // if (this instanceof Var)
			return ((Var) this).getName();
		}
	}

	public static void main(String[] args) {

		//String str = "a b ! c d & | &";
		String str = "a b | ! c & d | ! e &";
		//String str = "a b | ! c & ";
		System.out.println("input	= " + str);
		BooleanExpression expr = parseExpression(str);
		String str2 = expr.toPostfixString();
		System.out.println("postfix = " + str2);
		BooleanExpression dnf = expr.toDNF();
		String str3 = dnf.toStringDNF();
		System.out.println("DNF     = " + str3);
	}
}
