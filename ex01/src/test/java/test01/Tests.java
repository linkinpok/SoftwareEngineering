package test01;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

import ex01.And;
import ex01.BooleanExpression;
import ex01.Not;
import ex01.Or;
import ex01.Var;

class Tests {

	String str1 = "a b ! c d & | &";
	String str2 = "a b | ! c & d | ! e &";
	
	BooleanExpression expr1, expr2;
	BooleanExpression expr1_dnf, expr2_dnf;
	
	String str1_dnf = "a & !b | a & c & d";
	String str2_dnf = "a & !d & e | b & !d & e | !c & !d & e";
		
	String toStringDNF(BooleanExpression expr) {
		if (expr instanceof And) {
			return toStringDNF(((And) expr).getLeftOp()) + " & " + toStringDNF(((And) expr).getRightOp());
		} else if (expr instanceof Or) {
			return toStringDNF(((Or) expr).getLeftOp()) + " | " + toStringDNF(((Or) expr).getRightOp());
		} else if (expr instanceof Not) {
			return "!" + toStringDNF(((Not) expr).getOp());
		} else { // if (expr instanceof Var)
			return ((Var) expr).getName();
		}
	}

	@Test
	void allTests() {
		
		expr1 = BooleanExpression.parseExpression(str1);
		expr2 = BooleanExpression.parseExpression(str2);
		
		assertEquals(str1, expr1.toPostfixString());
		assertEquals(str2, expr2.toPostfixString());
		
		expr1_dnf = expr1.toDNF();
		expr2_dnf = expr2.toDNF();
		
		assertEquals(str1_dnf, toStringDNF(expr1_dnf));
		assertEquals(str2_dnf, toStringDNF(expr2_dnf));
	}

}

