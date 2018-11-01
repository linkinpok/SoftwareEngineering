package test01;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

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
		
	String toStringDNF2(BooleanExpression expr) {
		if (expr instanceof And) {
			return toStringDNF2(((And) expr).getLeftOp()) + " & " + toStringDNF2(((And) expr).getRightOp());
		} else if (expr instanceof Or) {
			return toStringDNF2(((Or) expr).getLeftOp()) + " | " + toStringDNF2(((Or) expr).getRightOp());
		} else if (expr instanceof Not) {
			return "!" + toStringDNF2(((Not) expr).getOp());
		} else { // if (expr instanceof Var)
			return ((Var) expr).getName();
		}
	}

	@Test
	void testDNF() {
		
		expr1 = BooleanExpression.parseExpression(str1);
		expr2 = BooleanExpression.parseExpression(str2);
		
		assertEquals(str1, expr1.toPostfixString());
		assertEquals(str2, expr2.toPostfixString());
		
		expr1_dnf = expr1.toDNF();
		expr2_dnf = expr2.toDNF();
		
		assertEquals(str1_dnf, toStringDNF2(expr1_dnf));
		assertEquals(str2_dnf, toStringDNF2(expr2_dnf));
	}

	// convert a number to a map of boolean to prepare for next test
		Map<String, Boolean> convertToMap (int number){
			Map<String, Boolean> map = new HashMap<String,Boolean>();
			map.put("e", number%2 == 1); number /= 2;
			map.put("d", number%2 == 1); number /= 2;
			map.put("c", number%2 == 1); number /= 2;
			map.put("b", number%2 == 1); number /= 2;
			map.put("a", number%2 == 1); number /= 2;
			return map;
		}
		
		@Test
		void testEvaluate() {		
			for (int number = 0; number < 32; number++) {
				Map<String, Boolean> map = convertToMap(number);
				//System.out.println(map);
				boolean a = map.get("a");
				boolean b = map.get("b");
				boolean c = map.get("c");
				boolean d = map.get("d");
				boolean e = map.get("e");
				
				// test expr2_dnf
				boolean expect = a & !d & e | b & !d & e | !c & !d & e;
				expr2 = BooleanExpression.parseExpression(str2);
				boolean input1 = expr2.evaluate(map);
				expr2_dnf = expr2.toDNF();
				boolean input2 = expr2_dnf.evaluate(map);
				assertEquals(expect,input1);
				assertEquals(expect,input2);
				//if(expect==input1 && expect ==input2)
				//	System.out.println("Test " + number + ": passed");
			}

		}

	}
