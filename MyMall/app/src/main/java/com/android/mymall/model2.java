package com.android.mymall;

/**
 * Created by Ashwin on 25-02-2018.
 */

public class model2 {

    String name,url,language;


    public model2() {

    }

    public model2(String name, String url, String language) {
        this.name = name;
        this.url = url;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "model2{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
