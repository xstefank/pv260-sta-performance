package cz.muni.fi.pv260.chkstyle.check.reporter;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public abstract class AbstractCheckReporter implements CheckReporter {

    @Override
    public void leaveToken(DetailAST ast) {
        //not required
    }


}
