package ua.nure.faryha.practice5;

/*
Создать класс Spam, который получает в конструкторе массив сообщений и
согласованный с ним массив интервалов времени в миллисекундах и
выводит одновременно соответствующие сообщения на экран через заданные интервалы времени.
По нажатию на Enter приложение должно завершать свою работу (данную функциональность поместить в метод Spam.main).
Входные данные (массив сообщений и массив пауз) записывать в коде класса Spam. Количество элементов в каждом из массивов - минимум 2, их можно взять из примера:

Пример входной информации (класс Spam)
-------------------------------------------------------
String[] messages = new String[] { "@@@", "bbbbbbb" };
int[] times = new int[] { 333, 222 };
-------------------------------------------------------

Для того, чтобы отследить нажатие на Enter достаточно считывать консольный ввод и анализировать содержимое.
Если чтение будет осуществлено с помощью классов Scanner / BufferedReader,
то признаком Enter является пустая строка, которую возвращают, соответственно,
методы Scanner#nextLine() / BufferedReader#readLine().
 */

public class Spam {
    String[] messages;
    int[] times;
   // String[] messages = new String[] { "@@@", "bbbbbbb" };
   // int[] times = new int[] { 333, 222 };

    Spam(String[]messages, int[]times){
        this.messages=messages;
        this.times=times;
     //   System.out.println("mess[0] за time[0] время");
     //   System.out.println("mess[1] за time[1] время");
    }
    

    public static void main(String[] args) {
    String messages[] = { "@@@", "bbbbbbb" };
    int times[]= { 333, 222 };
    Spam sp = new Spam(messages, times);
    //sp.start;

    }
}
