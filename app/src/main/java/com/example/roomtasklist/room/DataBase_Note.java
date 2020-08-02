package com.example.roomtasklist.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.roomtasklist.room.dao.Dao_Note;
import com.example.roomtasklist.room.dao.Dao_Task;
import com.example.roomtasklist.room.entity.Note;
import com.example.roomtasklist.room.entity.Task;

@Database(entities = {Note.class, Task.class}, version = 1)
public abstract class DataBase_Note extends RoomDatabase {
    public abstract Dao_Note noteDao();
    public abstract Dao_Task taskDao();
}
