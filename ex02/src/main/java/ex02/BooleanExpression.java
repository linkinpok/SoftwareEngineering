package ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public interface BooleanExpression {
    static BooleanExpression parseExpression(String arg) {
        Stack<BooleanExpression> stack = new Stack<>();

        // Parse argument string char by char
        for (int i = 0; i < arg.length(); i++) {
            char current = arg.charAt(i);

            // Check current character
            switch (current) {
                // If the current char is a space, just continue
                case ' ':
                    continue;

                    // And operator
                case '&':
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException();
                    }

                    // We have to swap the operands from the stack to
                    // get the correct result in toPostfixString().
                    // Example: Parsing a b &
                    // Stack (growing upwards):
                    // b
                    // a
                    // As you can see, the first stack pop must be the
                    // second operand.
                    BooleanExpression andRightOp = stack.pop();
                    BooleanExpression andLeftOp = stack.pop();

                    stack.push(new And(andLeftOp, andRightOp));
                    break;

                // Or operator
                case '|':
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException();
                    }

                    BooleanExpression orRightOp = stack.pop();
                    BooleanExpression orLeftOp = stack.pop();

                    stack.push(new Or(orLeftOp, orRightOp));
                    break;

                // Not operator
                case '!':
                    if (stack.size() < 1) {
                        throw new IllegalArgumentException();
                    }

                    stack.push(new Not(stack.pop()));
                    break;

                // If all other cases above do not evaluate, it must be a variable
                default:
                    int nextSpacePos = arg.indexOf(' ', i);

                    // If no space was found, take the substring until the end
                    if (nextSpacePos == -1) {
                        nextSpacePos = arg.length();
                    }

                    stack.push(new Var(arg.substring(i, nextSpacePos)));
                    i = nextSpacePos;
                    break;
            }
        }

        // If the stack size is not equal to 1, something went wrong (e.g. end of arg)
        if (stack.size() != 1) {
            throw new IllegalArgumentException();
        }

        return stack.pop();
    }

    String toPostfixString();

    // TODO Problem 3a) Implementieren Sie die folgende Methode in And/Or/Not/Var
    String toPrefixString();
    // 20 minutes for implementation + test

    // TODO Problem 3b) Implementieren Sie die folgende Methode in And/Or/Not/Var
    String toInfixString();
    // 35 minutes for implementation + test

    boolean evaluate(Map<String, Boolean> argumentMap);

    default List<BooleanExpression> disjunctiveTerms() {
        List<BooleanExpression> list = new ArrayList<>();

        // Adds this BooleanExpression to the list
        list.add(this);

        return list;
    }

    BooleanExpression toDNF();

    // These default methods have to be overwritten in the
    // correct class.
    default boolean isAnd() { return false; }
    default boolean isOr() { return false; }
    default boolean isNot() { return false; }
    default boolean isVar() { return false; }

    // Methods to get rid of casting, must be overwritten in the
    // correct class. Var is not casted to anywhere, so omitted here
    default And asAnd() { throw new UnsupportedOperationException(); }
    default Or asOr() { throw new UnsupportedOperationException(); }
    default Not asNot() { throw new UnsupportedOperationException(); }

	
    
    /******* TEST *******/
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

		String str, prefix_expect, infix_expect;
		/*
		str = "a b ! c d & | &";
		prefix_expect = "& a | ! b & c d";
		infix_expect = "a & (!b | c & d)";
		*/
		str = "a b | ! c & d | ! e &";
		prefix_expect = "& ! | & ! | a b c d e";
		infix_expect = "!(!(a | b) & c | d) & e";

		BooleanExpression expr = parseExpression(str);
		String postfix = expr.toPostfixString();
		System.out.println("postfix = " + postfix);
		String prefix = expr.toPrefixString();
		System.out.println("prefix  = " + prefix);
		String infix = expr.toInfixString();
		System.out.println("infix   = " + infix);
		/*
		BooleanExpression dnf = expr.toDNF();
		String str3 = dnf.toStringDNF();
		System.out.println("DNF     = " + str3);
		*/
	}
}
