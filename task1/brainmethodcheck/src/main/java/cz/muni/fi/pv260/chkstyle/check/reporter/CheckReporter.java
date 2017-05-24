package cz.muni.fi.pv260.chkstyle.check.reporter;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public interface CheckReporter extends CheckListener {

    CheckReport getCheckReport();

    void clearReport();
}
