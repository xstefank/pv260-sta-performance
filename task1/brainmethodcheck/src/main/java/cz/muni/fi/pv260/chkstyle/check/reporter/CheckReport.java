package cz.muni.fi.pv260.chkstyle.check.reporter;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class CheckReport {

    private static final String PASSED = "PASSED";
    private static final String FAILED = "FAILED";

    private boolean passed;
    private String name;
    private String message;

    public CheckReport(boolean passed, String name, String message) {
        this.passed = passed;
        this.name = name;
        this.message = message;
    }

    public boolean passed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append(passed ? PASSED : FAILED);
        if (!passed) {
            output.append(" - ").append(message);
        }
        output.append(" [").append(name).append("]");
        return output.toString();
    }
}
