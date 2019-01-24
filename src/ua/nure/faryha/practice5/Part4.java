package ua.nure.faryha.practice5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

//В обязательном порядке в обоих вариантах каждую операцию сравнения снабдить задержкой в 1 мс!???????
/*
Распараллелить задачу поиска максимального значения в матрице целых чисел MxN (загружать из файла)
при помощи M потоков. Дополнительно решить задачу поиска максимального значения без распараллеливания.
Вывести результат и время выполнения кода (в миллисекундах) для обоих вариантов.
MAX
TIME
MAX2
TIME2
*/
public class Part4 {

    public int massive[][];
    private static final String ENCODING = "CP1251";

    //========getInput for one thread
    public static ArrayList<Integer> getInput(String fileName) throws FileNotFoundException {
        ArrayList<Integer> listI = new ArrayList<Integer>();
        Scanner sc = new Scanner(new File(fileName), ENCODING);
        while (sc.hasNextInt()) {
            listI.add(sc.nextInt());
        }
        sc.close();
        return listI;
    }
    //===end of the one thread-part


    //=========getInput for multilines
    public static int strok = 0;
    public static int colonok = 0;
    public static int maxV = 0;

    public static String getInputForString(String fileName) throws FileNotFoundException {
        int str = 0;
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileName), ENCODING);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
            str = str + 1;
            sb.append(System.lineSeparator());
        }
        scanner.close();
        strok = str;
        return sb.toString().trim();
    }

    //finding max value and probably compare

    public static void maxValue(String strokmax) {
        String slok = strokmax;
        //  System.out.println(slok);
        Pattern p3 = Pattern.compile("\\d+");
        Matcher m3 = p3.matcher(slok);
        //   int min = Integer.MAX_VALUE;
        //   int max = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        while (m3.find()) {
            int nextNum = Integer.valueOf(m3.group());
            if (nextNum > max) {
                max = nextNum;
            }
        }
        //  maxV = max;
        if (maxV < max) {
            maxV = max;
        }
        //    System.out.println("max "+ max);
        //   System.out.println("maxv " + maxV);
    }

    //thread making
    static class newThread extends Thread {
        public String sForMaxValue;

        newThread(String s) {
            sForMaxValue = s;
            start();
        }

        public void run() {
            synchronized (this) {
                // System.out.println(currentThread().getName());
                Part4.maxValue(sForMaxValue);
            }
        }
    }
    //end of the multilines part

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        //=================one thread===============
        ArrayList<Integer> input = Part4.getInput("part4.txt");
        //   System.out.println(input);
        long timeBefore = System.currentTimeMillis();
        Thread.sleep(0, 1);
        System.out.println(Collections.max(input));
        long timeAfter = System.currentTimeMillis();
        System.out.println(timeAfter - timeBefore);

        //===============many threads================
        //  ArrayList<String> inputForString = new ArrayList<String>();
        String strInput = Part4.getInputForString("part4.txt");
        //  System.out.println(strInput);
        // finding colomns(rightcol) ans rows(strok)
        //    System.out.println("strokkk " + strok);
        int col = 0;
        Pattern p = Pattern.compile("\\w+");
        Matcher m = p.matcher(strInput);
        while (m.find()) {
            col = col + 1;
        }
        colonok = col / strok;
        //    System.out.println("colonok " + colonok);
        String massiveOfString[] = new String[strok];
        massiveOfString = strInput.split("\n");

        //making many threads
        long timeBefore2 = System.currentTimeMillis();
        for (int i = 0; i < strok; i++) {
            new newThread(massiveOfString[i]);
        }
        newThread.sleep(2);
        System.out.println(maxV);
        long timeAfter2 = System.currentTimeMillis();
        System.out.println(timeAfter2 - timeBefore2);
    }
}
