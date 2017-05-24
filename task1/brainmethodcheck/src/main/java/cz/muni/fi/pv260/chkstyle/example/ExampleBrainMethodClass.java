package cz.muni.fi.pv260.chkstyle.example;

/**
 * Created by marek on 16/05/2017.
 */
public class ExampleBrainMethodClass {

    public void brainMethod(int p1, char p2) {
        //params and vars
        int v1, v2;
        int v3;

        //nesting and cyclomatic complexity
        for (;;) {
            while (true) {
                do {
                    break;
                } while (true);
                break;
            }
            break;
        }

        //too many SLOC
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void notBrainMethod() {
        System.out.println("Valid method");
        int x = 1;
        try {
            if (x > 0) {
                x++;
            }
        } catch (Exception e) {
            --x;
        }
    }
}
