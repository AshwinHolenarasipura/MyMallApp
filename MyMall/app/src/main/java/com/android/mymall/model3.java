package com.android.mymall;

/**
 * Created by Ashwin on 28-02-2018.
 */

public class model3 {

    String collectionname,stylename;
    int cost,number;

    public model3() {

    }

    public model3(String collectionname, String stylename, int cost, int number) {
        this.collectionname = collectionname;
        this.stylename = stylename;
        this.cost = cost;
        this.number = number;
    }

    public String getCollectionname() {
        return collectionname;
    }

    public void setCollectionname(String collectionname) {
        this.collectionname = collectionname;
    }

    public String getStylename() {
        return stylename;
    }

    public void setStylename(String stylename) {
        this.stylename = stylename;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "model3{" +
                "collectionname='" + collectionname + '\'' +
                ", stylename='" + stylename + '\'' +
                ", cost=" + cost +
                ", number=" + number +
                '}';
    }
}
