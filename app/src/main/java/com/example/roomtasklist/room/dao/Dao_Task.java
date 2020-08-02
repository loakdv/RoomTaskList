package com.example.roomtasklist.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.roomtasklist.room.entity.Task;

import java.util.List;

@Dao
public interface Dao_Task {

    //Запросы из БД

    //Получить все данные из таблицы
    @Query("SELECT * FROM Task")
    List<Task> getAll();

    //Получить данные по определённому id
    @Query("SELECT * FROM Task WHERE note_id= :id")
    List<Task> getById(long id);

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> liveGetAll();

    //Очистить всю таблицу
    @Query("DELETE FROM Task")
    void deleteAll();

    @Query("DELETE FROM Task WHERE id= :id")
    void deleteByid(long id);

    //Изменить номер по нужным значениям, поиск происходит по id
    @Query("UPDATE Task SET text= :text WHERE id= :id")
    void updateByid(String text, long id);

    @Query("SELECT * FROM Task WHERE note_id= :id")
    List<Task> getSize(long id);

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

}
