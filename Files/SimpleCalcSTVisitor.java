package exp;

import org.antlr.v4.runtime.tree.*;
import exp.SimpleExpMinusParser.*;

public class SimpleCalcSTVisitor extends SimpleExpMinusBaseVisitor<Integer> {

	String indent;
	
    @Override
	public Integer visit(ParseTree x) {
        String temp=indent;
        indent=(indent==null)?"":indent+"  ";
        int result = super.visit(x);
        indent=temp;
        return result; 
	}

	@Override
	public Integer visitProg(ProgContext ctx) {
		System.out.println(indent+"prog");
		return visit( ctx.exp() );
	}
	
	@Override
	public Integer visitExpProd1(ExpProd1Context ctx) {
		System.out.println(indent+"exp: prod1 with TIMES");
			return visit( ctx.exp(0) ) * visit( ctx.exp(1) );
	}

	@Override
	public Integer visitExpProd2(ExpProd2Context ctx) {
		System.out.println(indent+"exp: prod2 with MINUS");
			return visit( ctx.exp(0) ) - visit( ctx.exp(1) );
	}

	@Override
	public Integer visitExpProd3(ExpProd3Context ctx) {
		System.out.println(indent+"exp: prod3 with LPAR RPAR");
		return visit(ctx.exp());
	}

	@Override
	public Integer visitExpProd4(ExpProd4Context ctx) {
		int res= Integer.parseInt(ctx.NUM().getText());
		System.out.println(indent+"exp: prod4 with NUM "+res );
		return res;
	}
	
}
