package cz.muni.fi.pv260.chkstyle;

import com.puppycrawl.tools.checkstyle.api.Check;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.checks.sizes.MethodLengthCheck;

/**
 * Created by marek on 16/05/2017.
 *
 */
public class BrainMethodCheck extends Check {

    /**
     * A key is pointing to the warning message text in "messages.properties"
     * file.
     */
    public static final String MSG_KEY = "maxLen.method";

    private int maxMethodLines = 10;
    private int maxCyclomaticComplexity;
    private int maxNestingDepth;
    private int maxVariables;
    private boolean countEmpty = false;

    public void setCountEmpty(boolean countEmpty) {
        this.countEmpty = countEmpty;
    }

    private MethodLengthCheck methodLengthCheck;

    public void setMaxMethodLines(int maxMethodLines) {
        this.maxMethodLines = maxMethodLines;
    }

    public void setMaxCyclomaticComplexity(int maxCyclomaticComplexity) {
        this.maxCyclomaticComplexity = maxCyclomaticComplexity;
    }

    public void setMaxNestingDepth(int maxNestingDepth) {
        this.maxNestingDepth = maxNestingDepth;
    }

    public void setMaxVariables(int maxVariables) {
        this.maxVariables = maxVariables;
    }

    public BrainMethodCheck() {
        MethodLengthCheck methodLengthCheck = new MethodLengthCheck();
        methodLengthCheck.setFileContents(this.getFileContents());
        //methodLengthCheck.setSeverity(this.getSeverity());
        methodLengthCheck.setMax(10);
        this.methodLengthCheck = methodLengthCheck;
    }

    public int[] getDefaultTokens() {
        return new int[]{
                TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF
        };
    }

    @Override
    public void visitToken(DetailAST ast) {
        this.visitAsMethodLengthCheck(ast);
    }

    public void visitAsMethodLengthCheck(DetailAST ast) {
        final DetailAST openingBrace = ast.findFirstToken(TokenTypes.SLIST);
        if (openingBrace != null) {
            final DetailAST closingBrace =
                    openingBrace.findFirstToken(TokenTypes.RCURLY);
            final int length = getLengthOfBlock(openingBrace, closingBrace);
            if (length > maxMethodLines) {
                log(ast.getLineNo(), ast.getColumnNo(), getMaxMethodLinesLogMessage(length));
            }
        }
    }

    private String getMaxMethodLinesLogMessage(int length){
        return "Method length has exceeded the limit (expected limit:"
                + this.maxMethodLines + ", method length: "+ length +")";
    }

    /**
     * Returns length of code only without comments and blank lines.
     * @param openingBrace block opening brace
     * @param closingBrace block closing brace
     * @return number of lines with code for current block
     */
    private int getLengthOfBlock(DetailAST openingBrace, DetailAST closingBrace) {
        int length = closingBrace.getLineNo() - openingBrace.getLineNo() + 1;

        if (!countEmpty) {
            final FileContents contents = getFileContents();
            final int lastLine = closingBrace.getLineNo();
            for (int i = openingBrace.getLineNo() - 1; i < lastLine; i++) {
                if (contents.lineIsBlank(i) || contents.lineIsComment(i)) {
                    length--;
                }
            }
        }
        return length;
    }
}
