package Klienci;

import java.util.Random;


public class Klienci {
    int timeToNext;
    private Random random;

    public Klienci() {
        random = new Random();
        timeToNext = generateTimeToNext();
    }

    public int produce()
    {
        timeToNext=generateTimeToNext();
        int count = random.nextInt(4)+1;
        System.out.println("Do kas przybyło " + count + " klientów. Kolejni klienci przybędą za: " + timeToNext);
        return count;
    }

    public int getTimeToNext() {
        return timeToNext;
    }

    private int generateTimeToNext()
    {
        return random.nextInt(8)+3;
    }
}
