package cz.muni.fi.pv260.chkstyle.check.reporter;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class CheckReportFactory {

    public static CheckReport newPassingCheck(String checkName) {
        return new CheckReport(true, checkName, "");
    }
}
