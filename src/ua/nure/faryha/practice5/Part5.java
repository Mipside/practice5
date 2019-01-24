package ua.nure.faryha.practice5;

/*
Создать k потоков, которые одновременно пишут в один и тот же файл символы:
1)первый поток записывает цифру 0 ровно 20 раз на 1й строке файла;
2)второй поток записывает цифру 1 ровно 20 раз на 2й строке файла;
...
3)десятый поток записывает цифру 9 ровно 20 раз на 10й строке файла.
----------
Требования к реализации.
(1) В обязательном порядке запись каждой цифры снабдить паузой в 1 мс!
(2) Для записи использовать класс RandomAccessFile.
(3) Допускается использование не более одного объекта класса RandomAccessFile!
(4) Перед началом работы файл в который будет происходить запись должен быть удален, если он существует.
(5) Главный поток, после запуска дочерних потоков на выполнение, должен дождаться их завершения, после чего вывести в консоль содержимое файла.

Замечания.
(1) Метод RandomAccessFile#seek(long) позволяет передвигать указатель внутри файла.
Каждый поток должен знать в каком месте файла ему записывать информацию.
Так как в условии фигурирует термин "строка", следует вывод каждого потока завершать ограничителем
строки, который выводить кросс-платформенным образом.
(2) Для того, чтобы записать некоторую цифру можно использовать выражение '0'+ n,
где n - цифра от 0 до 9 включительно.
Передвижение указателя внутри файла и запись информации необходимо синхронизировать.
20 цифр вывести на каждой строке
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


class newThreads extends Thread {
    public int numb;
    public long pointer;
    public static String newline = System.getProperty("line.separator");
    public RandomAccessFile raf;

    public newThreads(int numb, RandomAccessFile raf) throws InterruptedException {
        this.numb = numb;
        pointer = numb * 21;
        this.raf=raf;
       //     start();
     //  Thread.sleep(1);

    }

    public void run() {
        synchronized (raf){
        for (int i = 0; i <= 20; i++) {
            try {
                raf.seek(pointer + i);
                raf.writeBytes(String.valueOf(numb));
                raf.seek(pointer + 20);
                raf.writeBytes(newline);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
    }}


public class Part5 {
    public static final String ENCODING = "CP1251";
    public File newFile;
    public static RandomAccessFile raf;

    public Part5(String f) {
        File file = new File(f);
        if (file.exists()) {
            file.delete();
        }
        newFile = new File(f);
    }

    public static String getInput(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(fileName), ENCODING);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(System.lineSeparator());
        }
        scanner.close();
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Part5 part5 = new Part5("part5.txt");
        // part5.raf();
        int ten = 10;
        raf = new RandomAccessFile(part5.newFile, "rw");

     /*   for (int i = 0; i < ten; i++) {
            new newThreads(i, raf);
        }
*/
     Thread test[] = new Thread[ten];
        //newThread test[] = new newThread[ten];

        /*
        for(int i = 0; i < test.length; i++)
        {
            test[i] = new newThreads(i,raf);
            test[i].start();
            test[i].join();
          //  System.out.println(Thread.currentThread().getName());
        }*/
        Thread[] threads = new Thread[10];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new newThreads(i, raf);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        try {
            System.out.println(Part5.getInput("part5.txt"));
        } catch (IOException e) {
            return;
        }
        try {
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


