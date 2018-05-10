package it.sharedservices.rxandroidroom.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Comment {
    @PrimaryKey
    private int id;
    private String value;

    public int id() {
        return id;
    }

    public void id(int pId) {
        id = pId;
    }

    public String value() {
        return value;
    }

    public void value(String pValue) {
        value = pValue;
    }
}
