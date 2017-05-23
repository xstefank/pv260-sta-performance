package cz.muni.fi.pv260.chkstyle.check.reporter;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Created by marek on 16/05/2017.
 */
public class MethodMaxVariablesReporter extends AbstractCheckReporter {

    private static final int DEFAULT_MAXIMUM_VALUE = 5;

    private CheckReport checkReport;

    private int max = DEFAULT_MAXIMUM_VALUE;

    public MethodMaxVariablesReporter(int max) {
        this.max = max;
        clearReport();
    }

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

            int variableCount = getTokensCount(ast, TokenTypes.VARIABLE_DEF);
            variableCount += getTokensCount(ast, TokenTypes.PARAMETER_DEF);

            if (variableCount > max) {
                checkReport = new CheckReport(false, this.getClass().getSimpleName(),
                        String.format("Invalid number of used variables %d (maximum is %d)", variableCount, max));
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

    @Override
    public CheckReport getCheckReport() {
        return checkReport;
    }

    @Override
    public void clearReport() {
        checkReport = CheckReportFactory.newPassingCheck(this.getClass().getSimpleName());
    }
}
