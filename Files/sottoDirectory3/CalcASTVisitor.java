package compiler;

import compiler.AST.*;
import compiler.lib.*;

public class CalcASTVisitor extends BaseASTVisitor<Integer> {

	CalcASTVisitor() {}
	CalcASTVisitor(boolean debug) { super(debug); } // enables print for debugging

	@Override
	public Integer visitNode(ProgNode n) {
		if (print) printNode(n);
		return visit(n.exp);
	}

	@Override
	public Integer visitNode(PlusNode n) {
		if (print) printNode(n);
		return visit(n.left) + visit(n.right);
	}

	@Override
	public Integer visitNode(TimesNode n) {
		if (print) printNode(n);
		return visit(n.left) * visit(n.right);
	}

	@Override
	public Integer visitNode(IntNode n) {
		if (print) printNode(n,": "+n.val);
		return n.val;
	}

	@Override
	public Integer visitNode(EqualsNode n) {
		if (print) printNode(n);
		if(visit(n.left) == visit(n.right)){
			return 0;
		}
		return 1;
	}

	@Override
	public Integer visitNode(BoolNode n) {
		if (print) printNode(n);
		if(n.boolValue){
			return 0;
		}
		return 1;
	}

	@Override
	public Integer visitNode(IfNode n) {
		if (print) printNode(n);
		if(visit(n.conditionNode) == 0){
			return visit(n.thenNode);
		}else{
			return visit(n.elseNode);
		}
	}

	@Override
	public Integer visitNode(PrintNode n) {
		if (print) printNode(n);
		Integer result = visit(n.toPrint);
		System.out.println(result);
		return result;
	}



}


