package Produkty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Produkty {
    int timeToNext;
    private Random random;

    public Produkty() {
        random = new Random();
        timeToNext = generateTimeToNext();
    }

    public int produce()
    {
        timeToNext=generateTimeToNext();
        int count = random.nextInt(3)+1;
       // int count2 = random.nextInt(2)+1;
       // List listOfCount = new ArrayList<>();
       // listOfCount.add(count);
       // listOfCount.add(count2);
        System.out.println("Wylosowana liczba produktów dla klienta pierwszego w kolejce wynosi: " + count + " produktów. Kolejna liczba produktów zostanie wylosowana za: " + timeToNext);
        //System.out.println("Wylosowana liczba produktów dla klienta pierwszego w kolejce B wynosi: " + count + " produktów. Kolejna liczba produktów zostanie wylosowana za: " + timeToNext);
        return count;
    }

    public int getTimeToNext() {
        return timeToNext;
    }

    private int generateTimeToNext()
    {
        return random.nextInt(3)+1;
    }
}
