package com.example.roomtasklist;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Activity_Main extends AppCompatActivity implements Adapter_Notes.CallBackButtons{

    List<Note> list_notes;

    Button btn_Clear, btn_Add;

    RecyclerView rv_Notes;

    DataBase_Note dbNote;

    Adapter_Notes a_notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Заметки");
        setContentView(R.layout.activity_main);

        dbNote= Room.databaseBuilder(getApplicationContext(),
                DataBase_Note.class, "note_database").allowMainThreadQueries().build();

        initializeList();
        initializeRecycleView();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnAddNote:
                        Intent add=new Intent(Activity_Main.this, Activity_Dialog_NewNote.class);
                        startActivity(add);
                        break;
                }
            }
        };


        btn_Add=findViewById(R.id.btnAddNote);
        btn_Add.setOnClickListener(oclBtn);
    }

    private void initializeList(){
        if(!(dbNote.noteDao().getAll()==null)){
            //Присваиваем локальному списку данные базы
            list_notes = dbNote.noteDao().getAll();
        }
    }

    private void initializeRecycleView(){
        rv_Notes=findViewById(R.id.rv_Notes);
        a_notes=new Adapter_Notes(getBaseContext(), list_notes, this);
        rv_Notes.setAdapter(a_notes);
        rv_Notes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    @Override
    public void deleteNote(Note note) {
        dbNote.noteDao().delete(note);
        initializeList();
        initializeRecycleView();
    }

    @Override
    public void editNote(Note note) {

    }

    @Override
    public void open_tasks(Note note) {
        Intent tasks=new Intent(Activity_Main.this, Activity_Tasks.class);
        tasks.putExtra("id", note.getId());
        startActivity(tasks);
    }

    @Override
    protected void onStart() {
        super.onStart();
        onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializeList();
        initializeRecycleView();}
}
