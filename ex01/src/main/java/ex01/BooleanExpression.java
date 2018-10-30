package ex01;

import java.util.Stack;

interface BooleanExpression {

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
				op2 = stack.pop();
				op1 = stack.pop();
				stack.push(new And(op1, op2));
				i++;
				break;
			case '|':
				op2 = stack.pop();
				op1 = stack.pop();
				stack.push(new Or(op1, op2));
				i++;
				break;
			case '!':
				op = stack.pop();
				stack.push(new Not(op));
				i++;
				break;
			default:
				var = str.substring(i, str.indexOf(' '));
				stack.push(new Var(var));
				i += var.length();
				break;
			}
		}

		if (stack.size() != 1) {
			throw new IllegalArgumentException();
		}
		return stack.pop();
	}
		
	String toPostfixString();	
}

