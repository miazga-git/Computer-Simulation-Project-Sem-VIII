package Kasy;

import java.util.Random;

public class Kasy {
    int timeToNext;
    private Random random;

    public Kasy() {
        random = new Random();
        timeToNext = generateTimeToNext();
    }

    public int consume()
    {
        timeToNext=generateTimeToNext();
        int count = 1;
        System.out.println("Chcę zabrać po jednym kliencie z każdej kolejki. Czas do kolejnej próby pobrania klientów: " + timeToNext);
        return count;
    }

    public int getTimeToNext() {
        return timeToNext;
    }

    private int generateTimeToNext()
    {
        return (random.nextInt(2)+1);
    }
}
