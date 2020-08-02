package com.example.roomtasklist.activities.dialog;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomtasklist.room.DataBase_Note;
import com.example.roomtasklist.room.entity.Note;
import com.example.roomtasklist.R;

import java.util.Calendar;
import java.util.Date;

public class Activity_Dialog_NewNote extends AppCompatActivity {


    //Элементы жкрана
    Button btn_Cancel, btn_Commit;
    EditText et_Name, et_About;

    //База данных
    DataBase_Note dbNote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newnote);

        dbNote= Room.databaseBuilder(getApplicationContext(),
                DataBase_Note.class, "note_database").allowMainThreadQueries().build();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=et_Name.getText().toString();
                String about=et_About.getText().toString();
                Date currentTime= Calendar.getInstance().getTime();
                switch (v.getId()){
                    case R.id.btn_cancelNote:
                        finish();
                        break;
                    case R.id.btn_commitNote:
                        dbNote.noteDao().insert(new Note(name, about, currentTime.toString()));
                        finish();
                        break;
                }
            }
        };

        btn_Cancel=findViewById(R.id.btn_cancelNote);
        btn_Cancel.setOnClickListener(oclBtn);
        btn_Commit=findViewById(R.id.btn_commitNote);
        btn_Commit.setOnClickListener(oclBtn);
        et_Name=findViewById(R.id.et_NoteName);
        et_About=findViewById(R.id.et_NoteReview);
    }
}
