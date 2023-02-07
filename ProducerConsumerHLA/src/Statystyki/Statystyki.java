package Statystyki;

import sim.monitors.Diagram;
import sim.monitors.MonitoredVar;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Statystyki {
    int timeToNext;
    private Random random;
    double sredniaDlugoscKolejkiDlaA = 0.0;
    double sredniaDlugoscKolejkiDlaB = 0.0;

    int lacznaLiczbaKlientowWKolejceA = 0;
    int lacznaLiczbaKlientowWKolejceB = 0;

    int iloscZmianDlugosciDlaA = 0;
    int iloscZmianDlugosciDlaB = 0;

    int ostatniaOdnotowanaLiczbaKlienctowWKolejceA = 0;
    int ostatniaOdnotowanaLiczbaKlienctowWKolejceB = 0;

    int liczbaObsluzonychWkolejceA = 0;
    int liczbaObsluzonychWkolejceB = 0;

    int lacznyCzasOczekiwaniaWkolejceA = 0;
    int lacznyCzasOczekiwaniaWkolejceB = 0;

    int dodanoCzasDoA = 0;
    int dodanoCzasDoB = 0;

    double sredniCzasOczekiwaniaWKolejceA = 0.0;
    double sredniCzasOczekiwaniaWKolejceB = 0.0;

    int licznikWykonan = 0;
    double licznikWykonanDouble = 0.0;
    MonitoredVar dlugoscKolejkiA = new MonitoredVar();
    MonitoredVar czasOczekiwaniaWKolejceA = new MonitoredVar();
    MonitoredVar dlugoscKolejkiB = new MonitoredVar();
    MonitoredVar czasOczekiwaniaWKolejceB = new MonitoredVar();
//    Diagram diagramDlugosciKolejek = new Diagram(Diagram.DiagramType.DISTRIBUTION,"Zmiana długości kolejek");
//    Diagram diagramZajetosciKolejek = new Diagram(Diagram.DiagramType.DISTRIBUTION,"Zmiana zajętości kolejek");
    Diagram diagramDlugosciKolejek = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Zmiana długości kolejek");
    //Diagram diagramZajetosciKolejek = new Diagram(Diagram.DiagramType.TIME_FUNCTION,"Zmiana zajętości kolejek");
    public Statystyki() {
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

    public void wypiszStatystyki(int numberOfClientsInA,int numberOfClientsInB, int numberOfProductsInA, int numberOfProductsInB){
        licznikWykonanDouble += 1.0;
        dlugoscKolejkiA.setValue(numberOfClientsInA,licznikWykonanDouble);
        dlugoscKolejkiB.setValue(numberOfClientsInB,licznikWykonanDouble);

        if(numberOfClientsInA != ostatniaOdnotowanaLiczbaKlienctowWKolejceA){
            if(numberOfClientsInA<ostatniaOdnotowanaLiczbaKlienctowWKolejceA){
                liczbaObsluzonychWkolejceA++;
            }
            iloscZmianDlugosciDlaA++;

            lacznaLiczbaKlientowWKolejceA += numberOfClientsInA;
            ostatniaOdnotowanaLiczbaKlienctowWKolejceA = numberOfClientsInA;
        }
        if(numberOfClientsInB != ostatniaOdnotowanaLiczbaKlienctowWKolejceB){
            if(numberOfClientsInB<ostatniaOdnotowanaLiczbaKlienctowWKolejceB){
                liczbaObsluzonychWkolejceB++;
            }
            iloscZmianDlugosciDlaB++;

            lacznaLiczbaKlientowWKolejceB += numberOfClientsInB;
            ostatniaOdnotowanaLiczbaKlienctowWKolejceB = numberOfClientsInB;
        }
        System.out.println("Liczba klientów w kolejce A: "+ numberOfClientsInA);
        System.out.println("Liczba klientów w kolejce B: "+ numberOfClientsInB);
        if(iloscZmianDlugosciDlaA!=0){
            sredniaDlugoscKolejkiDlaA = lacznaLiczbaKlientowWKolejceA*1.0/iloscZmianDlugosciDlaA;
        }
        if(iloscZmianDlugosciDlaB!=0){
            sredniaDlugoscKolejkiDlaB = lacznaLiczbaKlientowWKolejceB*1.0/iloscZmianDlugosciDlaB;
        }
        System.out.println("Srednia liczba klientów w kolejce A: "+ sredniaDlugoscKolejkiDlaA);
        System.out.println("Srednia liczba klientów w kolejce B: "+ sredniaDlugoscKolejkiDlaB);

        if(liczbaObsluzonychWkolejceA>liczbaObsluzonychWkolejceB){
            System.out.println("Więcej klientów obsłużono w kolejce A");
        }
        else if(liczbaObsluzonychWkolejceA<liczbaObsluzonychWkolejceB){
            System.out.println("Więcej klientów obsłużono w kolejce B");
        }
        else{
            System.out.println("W obu kolejkach obsłużono tyle samo klientów");
        }
//        System.out.println("liczbaObsluzonychWkolejceA : "+ liczbaObsluzonychWkolejceA);
//        System.out.println("licznikWykonan : " + licznikWykonan);
//        System.out.println("numberOfProductsInA : "+ numberOfProductsInA );
//        System.out.println("dodanoCzasDoA : "+ dodanoCzasDoA);
//        System.out.println("lacznyCzasOczekiwaniaWkolejceA : "+lacznyCzasOczekiwaniaWkolejceA);

        if(numberOfProductsInA>0&&dodanoCzasDoA==0){
            lacznyCzasOczekiwaniaWkolejceA +=numberOfProductsInA;
            czasOczekiwaniaWKolejceA.setValue(numberOfProductsInA,licznikWykonanDouble);
            dodanoCzasDoA = 1;
        }
        else if(numberOfProductsInA == 0){
            dodanoCzasDoA = 0;
        }
        if(numberOfProductsInB>0&&dodanoCzasDoB==0){
            lacznyCzasOczekiwaniaWkolejceB +=numberOfProductsInB;
            czasOczekiwaniaWKolejceB.setValue(numberOfProductsInB,licznikWykonanDouble);
            dodanoCzasDoB = 1;
        }
        else if(numberOfProductsInB == 0){
            dodanoCzasDoB = 0;
        }
        if(liczbaObsluzonychWkolejceA!=0)
            sredniCzasOczekiwaniaWKolejceA = lacznyCzasOczekiwaniaWkolejceA * 1.0 / liczbaObsluzonychWkolejceA;
        if(liczbaObsluzonychWkolejceB!=0)
            sredniCzasOczekiwaniaWKolejceB = lacznyCzasOczekiwaniaWkolejceB * 1.0 / liczbaObsluzonychWkolejceB;

        System.out.println("Sredni czas oczekiwania w kolejce A wynosi: "+ sredniCzasOczekiwaniaWKolejceA);
        System.out.println("Sredni czas oczekiwania w kolejce B wynosi: "+ sredniCzasOczekiwaniaWKolejceB);
        licznikWykonan++;
        //System.out.println("=============================Licznik wykonan:====================================== "+licznikWykonan);
        if(licznikWykonan == 30){
            //System.out.println("===========WSZEDLEM============");
            diagramDlugosciKolejek.add(dlugoscKolejkiA, Color.RED,"Kolejka A");
            diagramDlugosciKolejek.add(dlugoscKolejkiB, Color.GREEN,"Kolejka B");
            diagramDlugosciKolejek.show();
            //licznikWykonan = 0;
//            diagramZajetosciKolejek.add(czasOczekiwaniaWKolejceA,Color.RED,"Kolejka A");
//            diagramZajetosciKolejek.add(czasOczekiwaniaWKolejceB,Color.GREEN,"Kolejka B");
//            diagramZajetosciKolejek.show();
        }


    }

    public int getTimeToNext() {
            return timeToNext;
        }
    private int generateTimeToNext()
        {
            return (random.nextInt(2)+1);
        }
}//34687469236894323486
