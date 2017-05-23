package cz.muni.fi.pv260.chkstyle.check.reporter;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class CheckContext {

    private boolean inMethod;
    private DetailAST ast;

    public CheckContext(boolean inMethod, DetailAST ast) {
        this.inMethod = inMethod;
        this.ast = ast;
    }

    public boolean isInMethod() {
        return inMethod;
    }

    public void setInMethod(boolean inMethod) {
        this.inMethod = inMethod;
    }

    public DetailAST getAst() {
        return ast;
    }

    public void setAst(DetailAST ast) {
        this.ast = ast;
    }
}
