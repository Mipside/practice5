package ua.nure.faryha.practice5;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws InterruptedException, IOException {
     //   System.out.println("========Part1");
        Part1.main(new String[]{});
        Thread.sleep(2000);
    //    System.out.println("========Part2");
        Part2.main(new String[]{});
    //    System.out.println("========Part3");
        Part3.main(new String[]{});
        Thread.sleep(10);
   //     System.out.println("========Part4");
        try {
            Part4.main(new String[]{});
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    //    System.out.println("========Part5");
        Part5.main(new String[]{});
    }
}
