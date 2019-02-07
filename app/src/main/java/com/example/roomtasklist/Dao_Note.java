package com.example.roomtasklist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Dao_Note {

    //Запросы из БД

    //Получить все данные из таблицы
    @Query("SELECT * FROM Note")
    List<Note> getAll();

    //Получить данные по определённому id
    @Query("SELECT * FROM Note WHERE id= :id")
    Note getById(long id);

    @Query("SELECT * FROM Note")
    LiveData<List<Note>> liveGetAll();

    //Очистить всю таблицу
    @Query("DELETE FROM Note")
    void deleteAll();

    @Query("DELETE FROM Note WHERE id= :id")
    void deleteByid(int id);

    //Изменить номер по нужным значениям, поиск происходит по id
    @Query("UPDATE Note SET name= :name, about= :about, date= :date WHERE id= :id")
    void updateByid(String name, String about, String date, long id);

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

}
