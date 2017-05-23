package cz.muni.fi.pv260.chkstyle.check.reporter;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface CheckListener {

    void visitToken(DetailAST ast);

    void leaveToken(DetailAST ast);

    int[] getDefaultTokens();
}
