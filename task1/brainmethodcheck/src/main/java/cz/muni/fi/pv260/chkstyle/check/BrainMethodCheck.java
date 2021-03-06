package cz.muni.fi.pv260.chkstyle.check;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import cz.muni.fi.pv260.chkstyle.check.reporter.CheckReporter;
import cz.muni.fi.pv260.chkstyle.check.reporter.CyclomaticComplexityReporter;
import cz.muni.fi.pv260.chkstyle.check.reporter.MethodLengthReporter;
import cz.muni.fi.pv260.chkstyle.check.reporter.MethodMaxVariablesReporter;
import cz.muni.fi.pv260.chkstyle.check.reporter.NestedDepthReporter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class BrainMethodCheck extends AbstractCheck {


    private static final String PASSED = "PASSED";
    private static final String FAILED = "FAILED";


    private int linesOfCode;
    private int cyclomaticComplexity;
    private int nestingDepth;
    private int maxVars;

    private List<CheckReporter> reporters;

    private Map<Integer, List<CheckReporter>> reportersTokenMap;

    @Override
    public void beginTree(DetailAST rootAST) {
        reporters = initReporters();
        reportersTokenMap = createTokenMapping();
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[]{
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
                TokenTypes.LITERAL_TRY,
                TokenTypes.LITERAL_CATCH,
                TokenTypes.QUESTION,
                TokenTypes.LAND,
                TokenTypes.LOR,
        };
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[0];
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    @Override
    public void visitToken(DetailAST ast) {
        reportersTokenMap.get(ast.getType()).forEach(r -> r.visitToken(ast));
    }

    @Override
    public void leaveToken(DetailAST ast) {

        reportersTokenMap.get(ast.getType()).forEach(r -> r.leaveToken(ast));

        if (ast.getType() == TokenTypes.METHOD_DEF) {
            reportMethod(ast);
            clearReports();
        }

    }

    private List<CheckReporter> initReporters() {
        return Arrays.asList(
                new MethodLengthReporter(linesOfCode, false, getFileContents()),
                new CyclomaticComplexityReporter(cyclomaticComplexity),
                new NestedDepthReporter(nestingDepth),
                new MethodMaxVariablesReporter(maxVars)
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
                    mapping.put(token, new ArrayList<>(Collections.singletonList(reporter)));
                }
            });
        });
        return mapping;
    }

    private void reportMethod(DetailAST ast) {
        boolean allReportsFailed = reporters.stream().allMatch(reporter -> reporter.getCheckReport().failed());

        if (allReportsFailed) {
            String methodName = ast.findFirstToken(TokenTypes.IDENT).getText();
            String reportString = createReportString(methodName);

            log(ast, reportString);
        }
    }

    private String createReportString(String methodName) {
        List<String> failedChecks = reporters.stream().filter(r -> r.getCheckReport().failed())
                .map(r -> r.getCheckReport().toString()).collect(Collectors.toList());

        return String.format("Method %s is a brain method ", methodName) + failedChecks.toString();
    }

    private void clearReports() {
        reporters.forEach(CheckReporter::clearReport);
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public void setCyclomaticComplexity(int cyclomaticComplexity) {
        this.cyclomaticComplexity = cyclomaticComplexity;
    }

    public void setNestingDepth(int nestingDepth) {
        this.nestingDepth = nestingDepth;
    }

    public void setMaxVars(int maxVars) {
        this.maxVars = maxVars;
    }
}
