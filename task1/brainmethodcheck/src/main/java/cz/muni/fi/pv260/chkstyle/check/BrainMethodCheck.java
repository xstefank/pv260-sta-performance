package cz.muni.fi.pv260.chkstyle.check;

import com.google.common.collect.ImmutableMap;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import cz.muni.fi.pv260.chkstyle.check.reporter.CheckContext;
import cz.muni.fi.pv260.chkstyle.check.reporter.CheckReport;
import cz.muni.fi.pv260.chkstyle.check.reporter.CheckReporter;
import cz.muni.fi.pv260.chkstyle.check.reporter.CyclomaticComplexityCheck;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class BrainMethodCheck extends AbstractCheck {

    private CheckContext context = new CheckContext(false, null);

    private int linesOfCode;

    private final List<CheckReporter> reportersList = Arrays.asList(
            new CyclomaticComplexityCheck(3)
    );

    private final Map<Integer, List<CheckReporter>> reporters = initReporters();

    private Map<Integer, List<CheckReporter>> initReporters() {
        Map<Integer, List<CheckReporter>> mapping = new HashMap<>();

        reportersList.forEach(reporter -> {
            int[] defaultTokens = reporter.getDefaultTokens();
            Arrays.stream(defaultTokens).forEach(token -> {
                if (mapping.get(token) != null) {
                    mapping.get(token).add(reporter);
                } else {
                    mapping.put(token, Collections.singletonList(reporter));
                }
            });
        });
        return mapping;
    }


    public int[] getDefaultTokens() {
        return new int[]{TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF};
    }

    @Override
    public void visitToken(DetailAST ast) {
        context.setAst(ast);
        reporters.get(ast.getType()).forEach(r -> r.visitToken(context));


//        if (methodLengthReport.passed()) {
//            logPassed(ast, methodLengthReport);
//        } else {
//            logFailed(ast, methodLengthReport);
//        }

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
