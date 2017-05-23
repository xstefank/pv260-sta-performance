package cz.muni.fi.pv260.chkstyle.check;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import cz.muni.fi.pv260.chkstyle.check.reporter.CheckReport;
import cz.muni.fi.pv260.chkstyle.check.reporter.MethodLengthReporter;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class BrainMethodCheck extends AbstractCheck {

    private int linesOfCode;

    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF };
    }

    @Override
    public void visitToken(DetailAST ast) {
        StringBuilder report = new StringBuilder();

        CheckReport methodLengthReport = new MethodLengthReporter(linesOfCode, false, getFileContents()).checkPassed(ast);

        if (methodLengthReport.passed()) {
            logPassed(ast, methodLengthReport);
        } else {
            logFailed(ast, methodLengthReport);
        }

    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    private void logPassed(DetailAST ast, CheckReport report) {
        setSeverity(SeverityLevel.INFO.toString());
        log(ast, report.toString());
    }

    private void logFailed(DetailAST ast, CheckReport report) {
        setSeverity(SeverityLevel.ERROR.toString());
        log(ast, report.toString());
    }
}
