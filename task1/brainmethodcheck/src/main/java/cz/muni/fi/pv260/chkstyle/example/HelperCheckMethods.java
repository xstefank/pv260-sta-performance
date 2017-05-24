package cz.muni.fi.pv260.chkstyle.example;

import java.util.Random;

/**
 * @author <a href="mailto:xstefank122@gmail.com">Martin Stefanko</a>
 */
public class HelperCheckMethods {

    public void tooManyVarsDef() {
        int x = 5;
        int y = 4;
        int z;
        int u;
        int w;
    }

    public void tooManyVarsOneLineDef() {
        int a, b, c, d, e;
    }

    public void tooManyParams(int a, int b, int c, int d, int e) {

    }

    public void tooManyVarsCombined(int a, int b) {
        int x;
        int y, z;
    }

    public void veryLongMethod() {
        System.out.println("This");
        System.out.println("Is");
        System.out.println("Thirty");
        System.out.println("Lines");
        System.out.println("Of");
        System.out.println("Code (at least)");

        System.out.println("This");
        System.out.println("Is");
        System.out.println("Thirty");
        System.out.println("Lines");
        System.out.println("Of");
        System.out.println("Code (at least)");

        System.out.println("This");
        System.out.println("Is");
        System.out.println("Thirty");
        System.out.println("Lines");
        System.out.println("Of");
        System.out.println("Code (at least)");

        System.out.println("This");
        System.out.println("Is");
        System.out.println("Thirty");
        System.out.println("Lines");
        System.out.println("Of");
        System.out.println("Code (at least)");

        System.out.println("This");
        System.out.println("Is");
        System.out.println("Thirty");
        System.out.println("Lines");
        System.out.println("Of");
        System.out.println("Code (at least)");
    }

    public void tooComplexMethod() {
        Random rand = new Random();
        int bound = 100;
        int x = rand.nextInt(bound);
        if (x < bound) {
            if (x < bound / 2) {
                if (x < bound / 4) {
                    System.out.println("three nested conditions here");
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            for (int k = 0; k < 10; k++) {
                                int y = i * j * k;
                                System.out.println("Result:" + y);
                            }
                        }
                    }
                }
            }
        }
    }

    public void nesterFor() {
        for (; ; ) {
            for (; ; ) {
                for (; ; ) {
                    break;
                }
                break;
            }
            break;
        }
    }

    public void nestedWhile() {
        while (true) {
            while (true) {
                while (true) {
                    break;
                }
                break;
            }
            break;
        }
    }

    public void nestedDoWhile() {
        do {
            do {
                do {
                    break;
                } while (true);
                break;
            } while (true);
            break;
        } while (true);
    }

    public void nestedIf() {
        if (true) {
            if (true) {
                if (true) {

                }
            }
        }
    }

    public void nestedSwitch() {
        switch (0) {
            default:
                switch (0) {
                    default:
                        switch (0) {
                            default:
                                break;
                        }
                }
        }
    }

    public void nestedTryCatch() {
        try {
            try {
                try {

                } catch (Exception e) {
                    //noop
                }
            } catch (Exception e) {
                //noop
            }
        } catch (Exception e) {
            //noop
        }
    }
}
