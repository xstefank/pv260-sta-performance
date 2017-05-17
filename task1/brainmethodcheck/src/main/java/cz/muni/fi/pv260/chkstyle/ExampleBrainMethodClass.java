package cz.muni.fi.pv260.chkstyle;

import java.util.Random;

/**
 * Created by marek on 16/05/2017.
 */
public class ExampleBrainMethodClass {

    public ExampleBrainMethodClass() {

    }

    public void TooMuchVariablesMethod() {
        int x = 5, y = 4, z, w;
        if (5 * x > 4 * y) {
            for (int i = 0; i <= 5; i++) {
                int a, b, c, d;
                a = 5;
                b = 5 * a;
                c = 5 * b;
                d = 5 * c;
                d /= 5;
                switch (d) {
                    case 25:
                        System.out.println("I can't count");
                        break;
                    case 75:
                        System.out.println("I can't count");
                        break;
                    case 125:
                        if (d == c) {
                            System.out.println("I can count and d == c");
                            String tenthVariable = "I am the 10th variable in this method";
                        }
                }
                System.out.println("This");
            }
        }
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

    public void tooManyConditionsMethod(){
        Random rand = new Random();
        int bound = 100;
        int x = rand.nextInt(bound);
        if(x < bound){
            if(x < bound/2){
                if(x < bound/4){
                    System.out.println("three nested conditions here");
                }
            }
        }
    }

    public void tooManyCyclesMethod(){
        for(int i = 0; i< 10; i++){
            for(int j = 0; j< 10; j++){
                for(int k = 0; k< 10; k++){
                    int x = i*j*k;
                    System.out.println("Result:" + x);
                }
            }
        }
    }

    public void tooManyTriesMethod() throws Exception{
        try{
            int x = 0;
            try{
                int y = 10;
                try{
                    int z = y/x;
                    System.out.println("Result of division by zero is:" + z);
                }catch(Exception e){
                    throw new Exception(e);
                }
            }catch(Exception e){
                throw new Exception(e);
            }
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    public void tooComplexMethod(){
        Random rand = new Random();
        int bound = 100;
        int x = rand.nextInt(bound);
        if(x < bound){
            if(x < bound/2){
                if(x < bound/4){
                    System.out.println("three nested conditions here");
                    for(int i = 0; i< 10; i++){
                        for(int j = 0; j< 10; j++){
                            for(int k = 0; k< 10; k++){
                                int y = i*j*k;
                                System.out.println("Result:" + y);
                            }
                        }
                    }
                }
            }
        }
    }
}
