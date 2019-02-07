package com.example.roomtasklist;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Note.class, Task.class}, version = 1)
public abstract class DataBase_Note extends RoomDatabase {
    public abstract Dao_Note noteDao();
    public abstract Dao_Task taskDao();
}
