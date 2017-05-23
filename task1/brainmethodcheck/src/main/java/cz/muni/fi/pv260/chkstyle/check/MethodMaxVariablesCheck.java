package cz.muni.fi.pv260.chkstyle.check;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Created by marek on 16/05/2017.
 */
public class MethodMaxVariablesCheck extends Check {

    private int max;

    public void setMax(int max) {
        this.max = max;
    }

    public int[] getDefaultTokens() {
        return new int[]{
                TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF
        };
    }

    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST openingBrace = ast.findFirstToken(TokenTypes.SLIST);
        if (openingBrace != null) {
            final DetailAST methodName = ast.findFirstToken(TokenTypes.IDENT);
            int variableCount = getTokensCount(ast, TokenTypes.VARIABLE_DEF);
            if (variableCount > max) {
                log(ast.getLineNo(), ast.getColumnNo(), "Method "
                        + methodName + " has too many variables."
                        + " Count: " + variableCount
                        + " Maximum is: " + max);
            }
        }
    }

    private int getTokensCount(DetailAST parent, int type) {
        int count = 0;
        for (DetailAST ast = parent.getFirstChild(); ast != null; ast = ast.getNextSibling()) {
            count += getTokensCount(ast, type);
            if (ast.getType() == type) {
                count++;
            }
        }
        return count;
    }

}
