package ua.nure.faryha.practice5;

/*создать дочерний поток, который бы в течении примерно 2 сек
печатал свое имя каждые полсекунды.
Сделать это 2я способами:
-при помощи расширения класса Thread;
-при помощи реализации интерфейса Runnable.
Вначале должна работать одна реализация, после ее
завершения должна отработать другая реализация.
*/

//==============Extend Thread realization
class newThreadOne extends Thread {
    newThreadOne() {
        //   System.out.println("newThreadOne " + this);
        start();
    }

    public void run() {
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(currentThread().getName());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("newThreadTwo прерван");
        }

    }
}


//===============Runnable realization
class newThreadTwo implements Runnable {
    Thread t;

    newThreadTwo() {
        //  t = new Thread(this, "newThreadTwo");
        t = new Thread(this, "Thread-1");
        //   System.out.println("newThreadTwo Thread " + t);   //first line
        t.start();
    }

    public void run() {
        //  System.out.println("in run method new thread two");  //third line
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println(t.getName());
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("newThreadTwo прерван");
        }
        //  System.out.println("end of newThreadTwo"); //after sout of 4 lines - this line
    }
}

public class Part1 {
    public static void main(String[] args) {

        new newThreadOne();
        try {
            newThreadOne.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new newThreadTwo();

        // System.out.println("current thread" + Thread.currentThread());  //second line is main thread


        //Another method...
        //   new newThreadOne();
    /* Thread newThreadOne = new Thread(){
       public void run(){

       }
     };
     */
    }
}
