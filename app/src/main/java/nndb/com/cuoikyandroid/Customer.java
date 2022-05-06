package nndb.com.cuoikyandroid;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Customer implements Serializable {

    @Exclude
    private String key;
    private String name;
    private String position;
    public Customer(){};
    public Customer(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}

