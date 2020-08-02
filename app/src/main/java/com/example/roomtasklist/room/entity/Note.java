package com.example.roomtasklist.room.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public long id;

    //Переменные/поля
    public String name;
    public String about;
    public int count;
    public String date;

    public Note(String name, String about, String date){
        this.name=name;
        this.about=about;
        this.date=date;
    }

    public long getId() {
        return  this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAbout() {
        return this.about;
    }

    public int getCount() {
        return this.count;
    }

    public String getDate() {
        return this.date;
    }
}
