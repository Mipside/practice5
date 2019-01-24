package ua.nure.faryha.practice5;
/*
Создать класс с двумя отдельными счетчиками и объект этого класса.
Создать несколько одинаковых потоков, каждый из которых повторяет следующее:
  * сравнивает значение счетчиков и печатает результат сравнения;
  * увеличивает первый счетчик;
  * засыпает на 10 мсек;
  * увеличивает второй счетчик.

Сравнить работу программы при условии, что код синхронизирован и не синхронизирован.
Реализовать следующую схему:
  * вначале отрабатывает один вариант;
  * после его завершения отрабатывает другой вариант.
Весь вывод должен быть небольшим - всего около нескольких десятков строк
 */

class CountersWork{
    private int counter1=110;
    private int counter2=10;

    public void countAndIncrement(){
        try {
            System.out.println(counter1-counter2);
            counter1=counter1+4;
            Thread.sleep(10);
            counter2=counter2+2;
        } catch (Exception e) {
            System.out.println("Thread  interrupted.");
        }
    }
}

//========================synchronized
    class newThread extends Thread{

        private Thread thread;
        private String threadName;
        CountersWork countersw;

        newThread(String name, CountersWork cw){
            threadName = name;
            countersw=cw;
        }

        public void run(){
            synchronized (countersw){
                countersw.countAndIncrement();
            }
        //    System.out.println("Thread " + threadName + " exiting");
        }
        public void start(){
          //  System.out.println("Starting " + threadName);
            if(thread == null){
                thread = new Thread(this,threadName);
                thread.start();
            }
        }
    }

//=============not synchronized
    class newThread2 extends Thread{
        private Thread thread2;
        private String threadName2;
        CountersWork countersw2;

        newThread2(String name, CountersWork cw){
            threadName2 = name;
            countersw2=cw;
        }
        public void run(){
            countersw2.countAndIncrement();
         //   System.out.println("Thread " + threadName2 + " exiting");
        }

        public void start(){
          //  System.out.println("Starting " + threadName2);
            if(thread2 == null){
                thread2 = new Thread(this,threadName2);
                thread2.start();
            }
        }

    }

public class Part3{

    public static void main(String[] args) {
        System.out.println("synchronized");
        CountersWork countersw = new CountersWork();
        CountersWork countersw2 = new CountersWork();

        newThread one = new newThread("Thread-1", countersw);
        newThread two = new newThread("Thread-2", countersw);

        one.start();
        two.start();

        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            newThread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("not synchronized");
        newThread2 thee = new newThread2( "Thread-3", countersw2);
        newThread2 four = new newThread2("Thread-4",countersw2);

        thee.start();
        four.start();

    }

}
