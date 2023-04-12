package compiler;

import compiler.AST.*;

public class PrintASTVisitor {

	public void visitNode(ProgNode n) {
		System.out.println("Prog");
	}

	public void visitNode(PlusNode n) {
		System.out.println("Plus");
	}

	public void visitNode(TimesNode n) {
		System.out.println("Times");
	}

	public void visitNode(IntNode n) {
		System.out.println("Int");  // must also print value!
	}
}






//	public void visit(Node n) { n.accept(this); } //performs the "n"-specific visit

//	String indent;
//
//	@Override
//	public void visit(Node n) {
//		String temp=indent;
//		indent=(indent==null)?"":indent+"  ";
//		n.accept(this);
//		indent=temp;
//	}

//	void printNode(Node n) {
//		System.out.println(indent+extractNodeName(n.getClass().getName()));
//	}
//
//	void printNode(Node n, String s) {
//		System.out.println(indent+extractNodeName(n.getClass().getName())+": "+s);
//	}
//
//	String extractNodeName(String s) { // s is in the form compiler.AST$NameNode
//		return s.substring(s.lastIndexOf('$')+1,s.length()-4);
//	}

//	PrintASTVisitor() { super(true); }


