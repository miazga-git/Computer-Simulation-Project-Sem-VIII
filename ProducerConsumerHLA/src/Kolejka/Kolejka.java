package Kolejka;


import java.sql.SQLOutput;

public class Kolejka {
    private int numberOfClietnsInQueueA;
    private int numberOfClietnsInQueueB;
    private int numberOfProductsForClientA;
    private int numberOfProductsForClientB;
    private int maxNumberOfClients;
    private int zabranoZA = 1;
    private int zabranoZB = 1;

    private static Kolejka instance = null;

    private Kolejka() {
        numberOfClietnsInQueueA=0;
        numberOfClietnsInQueueB=0;
        numberOfProductsForClientA=0;
        numberOfProductsForClientB=0;
        maxNumberOfClients=20;
    }
    static public Kolejka getInstance()
    {
        if(instance==null) instance = new Kolejka();
        return instance;
    }

    public int getNumberOfClietnsInQueueA() {
        return numberOfClietnsInQueueA;
    }

    public int getNumberOfClietnsInQueueB() {
        return numberOfClietnsInQueueB;
    }

    public int getNumberOfProductsForClientA() {
        return numberOfProductsForClientA;
    }

    public int getNumberOfProductsForClientB() {
        return numberOfProductsForClientB;
    }

    public int getMaxNumberOfClients() {
        return maxNumberOfClients;
    }

    public void setNumberOfClietnsInQueueA(int numberOfClietnsInQueueA) {
        this.numberOfClietnsInQueueA = numberOfClietnsInQueueA;
    }

    public void setNumberOfClietnsInQueueB(int numberOfClietnsInQueueB) {
        this.numberOfClietnsInQueueB = numberOfClietnsInQueueB;
    }

    public void setNumberOfProductsForClientA(int numberOfProductsForClientA) {
        this.numberOfProductsForClientA = numberOfProductsForClientA;
    }

    public void setNumberOfProductsForClientB(int numberOfProductsForClientB) {
        this.numberOfProductsForClientB = numberOfProductsForClientB;
    }

    public void setMaxNumberOfClients(int maxNumberOfClients) {
        this.maxNumberOfClients = maxNumberOfClients;
    }

    public boolean addTo(int count)
    {
        if(this.numberOfClietnsInQueueA+numberOfClietnsInQueueB+count<=this.maxNumberOfClients) {
            if(this.numberOfClietnsInQueueA>=this.numberOfClietnsInQueueB){
                this.numberOfClietnsInQueueB += count;
                System.out.println("Kolejka: Dodaj?? klient??w do kolejki B");
            }
            else{
                this.numberOfClietnsInQueueA += count;
                System.out.println("Kolejka: Dodaj?? klient??w do kolejki A");
            }
            System.out.println("Kolejka: Przyby??o " + count + " klient??w. Aktualna liczba klient??w: ");
            System.out.println("W kolejce A: " + this.numberOfClietnsInQueueA);
            System.out.println("W kolejce B: " + this.numberOfClietnsInQueueB);

            return true;
        }
        else
        {
            System.out.println("Kolejka: Nie ma miejsca w kolejce dla " + count + " klient??w");
            return false;
        }
    }

    public boolean addProductsTo(int count)
    {
        if(numberOfProductsForClientA == 0 && zabranoZA == 1){
            numberOfProductsForClientA = count;
            zabranoZA = 0;
        }
        if(numberOfProductsForClientB == 0 && zabranoZB == 1){
            numberOfProductsForClientB = count;
            zabranoZB = 0;
        }

        return true;
    }

    public boolean getFrom(int count)
    {
        boolean isTrue = false;
        if(numberOfClietnsInQueueA-count>=0) {
            if(numberOfProductsForClientA == 0){
                this.numberOfClietnsInQueueA-=count;
                System.out.println("Kolejka: W??a??nie obs??u??ono klienta w kolejce A. Aktualnie w kolejce A jest "+ this.numberOfClietnsInQueueA + " klient??w");
                isTrue = true;
                zabranoZA = 1;
            }
            else if(numberOfProductsForClientA >0){
                numberOfProductsForClientA--;
                System.out.println("Kolejka: W??a??nie skasowano produkt klienta w kolejce A. Aktualnie liczba produkt??w klienta w kolejce A wynosi: "+ this.numberOfProductsForClientA + " produkt??w");
                zabranoZA = 0;
            }

        }
        else if(numberOfClietnsInQueueA-count<0)
        {
            System.out.println("Kolejka: W kolejce A nie ma klient??w do obs??u??enia.");
            isTrue = false;
        }
        if(numberOfClietnsInQueueB-count>=0) {
            if(numberOfProductsForClientB == 0){
                this.numberOfClietnsInQueueB-=count;
                System.out.println("Kolejka: W??a??nie obs??u??ono klienta w kolejce B. Aktualnie w kolejce B jest "+ this.numberOfClietnsInQueueB + " klient??w");
                isTrue = true;
                zabranoZB = 1;
            }
            else if(numberOfProductsForClientB >0){
                numberOfProductsForClientB--;
                System.out.println("Kolejka: W??a??nie skasowano produkt klienta w kolejce B. Aktualnie liczba produkt??w klienta w kolejce B wynosi: "+ this.numberOfProductsForClientB + " produkt??w");
                zabranoZB = 0;
            }
        }
        else if(numberOfClietnsInQueueB-count<0)
        {
            System.out.println("Kolejka: W kolejce B nie ma klient??w do obs??u??enia.");
            isTrue = false;
        }
        return isTrue;

    }
}
