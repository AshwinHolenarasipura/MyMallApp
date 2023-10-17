package com.android.mymall;

/**
 * Created by Ashwin on 25-02-2018.
 */

public class model1 extends UserId{

    String mallname,mallurl;

    public model1() {

    }

    public model1(String mallname, String mallurl) {
        this.mallname = mallname;
        this.mallurl = mallurl;
    }

    public String getMallname() {
        return mallname;
    }

    public void setMallname(String mallname) {
        this.mallname = mallname;
    }

    public String getMallurl() {
        return mallurl;
    }

    public void setMallurl(String mallurl) {
        this.mallurl = mallurl;
    }

    @Override
    public String toString() {
        return "model1{" +
                "mallname='" + mallname + '\'' +
                ", mallurl='" + mallurl + '\'' +
                '}';
    }
}
