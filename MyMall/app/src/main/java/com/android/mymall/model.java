package com.android.mymall;

/**
 * Created by Ashwin on 23-02-2018.
 */

public class model extends UserId {

    String name, url, mall_id;

    public model() {

    }

    public model(String name, String url, String mall_id) {
        this.name = name;
        this.url = url;
        this.mall_id = mall_id;
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

    public String getMall_id() {
        return mall_id;
    }

    public void setMall_id(String mall_id) {
        this.mall_id = mall_id;
    }

    @Override
    public String toString() {
        return "model{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", mall_id='" + mall_id + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
