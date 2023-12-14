package ism.ase.ro.classes;

import ism.ase.ro.exceptions.*;

import java.util.ArrayList;

/*
 * SPECS
 *
 * name - between 5 and 200 alpha-numeric chars (no special chars)
 * price - between [0.01, 100000)
 * soldItems - between [0, 1000]
 */

public class Product {

    public static final float MIN_PRICE = 0.01f;
    public static final float MAX_PRICE = 100000f;
    public static final int MIN_SOLD_PRODUCTS = 0;
    public static final int MAX_SOLD_PRODUCTS = 1000;
    public static final int MIN_NAME_LENGHT = 5;
    public static final int MAX_NAME_LENGHT = 200;

    private String name;
    private float price;
    private ArrayList<Integer> weeklySoldItems; // number of items sold each week

    public Product(String name, float price) {
        this.setName(name);
        this.setPrice(price);
        weeklySoldItems = new ArrayList<Integer>();
    }

    public Product(String name, float price, ArrayList<Integer> soldItems) {
        this.setName(name);
        this.setPrice(price);
        this.setSales(soldItems);
    }

    public void setName(String name) {
        if (name.length() >= MIN_NAME_LENGHT && name.length() <= MAX_NAME_LENGHT) {
            this.name = name;
        } else
            throw new InvalidNameException();

    }

    public void setPrice(float price) {
        if (price >= MIN_PRICE && price < MAX_PRICE) {
            this.price = price;
        } else
            throw new InvalidPriceException();
    }

    public void setSales(ArrayList<Integer> soldItems) {
        if (soldItems == null)
            throw new NullArrayException();

        this.weeklySoldItems = new ArrayList<>();

        for (Integer i : soldItems) {
            if (i >= 0 && i <= 1000) {
                this.weeklySoldItems.add(i);
            } else {
                throw new InvalidSoldItemsException();
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Integer> getWeeklySoldItems() {
        return weeklySoldItems;
    }

    public float getPrice() {
        return this.price;
    }

    public void addWeek(int soldItems) {
        if (soldItems >= 0 && soldItems <= 1000)
            weeklySoldItems.add(soldItems);
        else
            throw new InvalidSoldItemsException();
    }

    public int getSoldItems(int i) {
        if (weeklySoldItems.get(i) >= 0 && weeklySoldItems.get(i) <= 1000)
            return weeklySoldItems.get(i);
        else
            throw new InvalidSoldItemsException();
    }

    /*
     *
     * determines the number of weeks with sales above the given limit determina
     * numarul de saptamani in care au fost vandute un numar de produse peste limita
     * data
     *
     */
    public int getNoWeeksAboveLimit(int minLimit) {
        int noWeeks = 0;

        if (minLimit <= 0)
            throw new InvalidLimitException();

        for (int n : weeklySoldItems) {
            if (n < 0 || n > 1000)
                throw new InvalidSoldItemsException();
            if (n >= minLimit)
                noWeeks++;
        }

        return noWeeks;
    }

    /*
     *
     * determines the percentage (%) of weeks with sales under the given limit from
     * total number of weeks determina procentul saptamanilor (din total saptamani)
     * care au avut vanzari sub limita data
     *
     */
    public int getPercentOfBadWeeks(int minLimit) {
        float m = 0;
        for (Integer n : weeklySoldItems)
            if (n < minLimit)
                m += 1;

        return (int) (100 * m / this.weeklySoldItems.size());
    }

    /*
     *
     *
     * determines the index of the weeks with maximum sales (multiple weeks can have
     * maximum sales) determina indexul saptamanilor cu vanzari maxime (mai multe
     * saptamani pot avea vanzari la nivel maxim)
     *
     *
     */

    public ArrayList<Integer> getWeeksIndexWithMaxSales() {
        ArrayList<Integer> maxWeeks = new ArrayList<>();
        int max = this.weeklySoldItems.get(0);

        for (Integer i : this.weeklySoldItems) {
            if (i > max) {
                max = i;
            }
        }

        for (int i = 0; i < this.weeklySoldItems.size(); i++)
            if (this.weeklySoldItems.get(i) >= max) {
                maxWeeks.add(i);
            }

        return maxWeeks;
    }

    @Override
    public String toString() {
        String output = this.name + " weekly sales: ";
        for (Integer n : weeklySoldItems)
            output += n + " ";
        return output;
    }
}
