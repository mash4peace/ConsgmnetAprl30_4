package Constractor;

import java.util.Date;

/**
 * Created by mash4 on 4/30/2017.
 */
public class Record {
    String name;
    int consgnrID;
    int item;
    int congmtID;

    public double getSalePrx() {
        return salePrx;
    }

    double salePrx;
    Date  date = new Date();

    public String getName() {
        return name;
    }



    public int getItem() {
        return item;
    }


    public Date getDate() {
        return date;
    }

    public Record(String congrName, int numbrOfItems, double salePrx, Date date ){
        this.name = congrName;
        this.salePrx = salePrx;
        this.item = numbrOfItems;
        this.date = date;

    }
    public Record(int consgnrID, String congrName, int numbrOfItems, double salePrx, Date date ){
        this.consgnrID = consgnrID;
        this.name = congrName;
        this.salePrx = salePrx;

        this.item = numbrOfItems;
        this.date = date;

    }



}
