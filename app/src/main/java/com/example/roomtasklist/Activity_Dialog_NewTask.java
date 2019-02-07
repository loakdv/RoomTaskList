package com.example.roomtasklist;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_Dialog_NewTask extends AppCompatActivity {


    Button btn_Cancel, btn_Confirm;
    EditText et_Task;

    DataBase_Note dbTasks;

    long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_newtask);

        id=getIntent().getLongExtra("id", 0);

        dbTasks = Room.databaseBuilder(getApplicationContext(),
                DataBase_Note.class, "note_database").allowMainThreadQueries().build();

        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 switch (v.getId()){
                     case R.id.btn_cancelTask:
                         finish();
                         break;
                     case R.id.btn_commitTask:
                         dbTasks.taskDao().insert(new Task(et_Task.getText().toString(), id));
                         finish();
                         break;
                 }
            }
        };

        btn_Cancel=findViewById(R.id.btn_cancelTask);
        btn_Cancel.setOnClickListener(oclBtn);
        btn_Confirm=findViewById(R.id.btn_commitTask);
        btn_Confirm.setOnClickListener(oclBtn);
        et_Task=findViewById(R.id.et_TaskReview);
    }
}
