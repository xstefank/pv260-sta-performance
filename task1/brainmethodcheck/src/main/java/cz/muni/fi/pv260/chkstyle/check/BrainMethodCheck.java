package cz.muni.fi.pv260.chkstyle.check;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.SeverityLevel;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import cz.muni.fi.pv260.chkstyle.check.reporter.CheckContext;
import cz.muni.fi.pv260.chkstyle.check.reporter.CheckReport;
import cz.muni.fi.pv260.chkstyle.check.reporter.CheckReporter;
import cz.muni.fi.pv260.chkstyle.check.reporter.CyclomaticComplexityCheck;

import java.util.ArrayList;
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
    private int cyclomaticComplexity;

    private List<CheckReporter> reporters;

    private Map<Integer, List<CheckReporter>> reportersTokenMap;

    private List<CheckReporter> initReporters() {
        return Arrays.asList(
                new CyclomaticComplexityCheck(cyclomaticComplexity)
        );
    }

    private Map<Integer, List<CheckReporter>> createTokenMapping() {
        Map<Integer, List<CheckReporter>> mapping = new HashMap<>();

        reporters.forEach(reporter -> {
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

    @Override
    public void beginTree(DetailAST rootAST) {
        reporters = initReporters();
        reportersTokenMap = createTokenMapping();
    }

    public int[] getDefaultTokens() {
        return new int[] {
                TokenTypes.CTOR_DEF,
                TokenTypes.METHOD_DEF,
                TokenTypes.INSTANCE_INIT,
                TokenTypes.STATIC_INIT,
                TokenTypes.LITERAL_WHILE,
                TokenTypes.LITERAL_DO,
                TokenTypes.LITERAL_FOR,
                TokenTypes.LITERAL_IF,
                TokenTypes.LITERAL_SWITCH,
                TokenTypes.LITERAL_CASE,
                TokenTypes.LITERAL_CATCH,
                TokenTypes.QUESTION,
                TokenTypes.LAND,
                TokenTypes.LOR,
        };
    }

    @Override
    public void visitToken(DetailAST ast) {
        context.setAst(ast);
        reportersTokenMap.get(ast.getType()).forEach(r -> r.visitToken(context));



//        if (methodLengthReport.passed()) {
//            logPassed(ast, methodLengthReport);
//        } else {
//            logFailed(ast, methodLengthReport);
//        }



    }

    @Override
    public void leaveToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.METHOD_DEF) {
            reportMethod(ast);

        }

        context.setAst(ast);
        reportersTokenMap.get(ast.getType()).forEach(r -> r.leaveToken(context));


    }

    private void reportMethod(DetailAST ast) {
        String methodName = ast.findFirstToken(TokenTypes.IDENT).getText();

        reporters.stream().filter(r -> !r.getCheckReport().passed()).forEach(r -> log(ast,
                String.format("Method %s %s", methodName, r.getCheckReport().toString())));
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public void setCyclomaticComplexity(int cyclomaticComplexity) {
        this.cyclomaticComplexity = cyclomaticComplexity;
    }

    private void logFailed(DetailAST ast, CheckReport report) {
        setSeverity(SeverityLevel.ERROR.toString());
        log(ast, report.toString());
    }
}
