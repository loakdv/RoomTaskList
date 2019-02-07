package com.example.roomtasklist;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = Note.class, parentColumns = "id", childColumns = "note_id", onDelete = CASCADE))
public class Task {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String text;

    public long note_id;

    public Task(String text, long note_id){
        this.text=text;
        this.note_id=note_id;
    }

    public long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public long getNote_id() {
        return this.note_id;
    }
}
