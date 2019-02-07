package com.example.roomtasklist;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class Activity_Tasks extends AppCompatActivity implements Adapter_Tasks.CallBackButtons {

    Button btn_backMain, btn_ClearTasks, btn_AddTask;

    List<Task> l_tasks;

    RecyclerView rv_Tasks;

    DataBase_Note dbNote;

    Adapter_Tasks a_tasks;

    private long last_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

            long id=getIntent().getLongExtra("id", 0);
            last_id=id;

            dbNote = Room.databaseBuilder(getApplicationContext(),
                    DataBase_Note.class, "note_database").allowMainThreadQueries().build();

        initializeList();
        initializeRecycleView();


        View.OnClickListener oclBtn=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_backToMain:
                        Intent main=new Intent(Activity_Tasks.this, Activity_Main.class);
                        startActivity(main);
                        break;
                    case R.id.btn_AddTask:
                        Intent add=new Intent(Activity_Tasks.this, Activity_Dialog_NewTask.class);
                        add.putExtra("id", last_id);
                        startActivity(add);
                        break;
                    case R.id.btn_ClearTascks:
                        break;
                }
            }
        };
        btn_backMain=findViewById(R.id.btn_backToMain);
        btn_backMain.setOnClickListener(oclBtn);
        btn_AddTask=findViewById(R.id.btn_AddTask);
        btn_AddTask.setOnClickListener(oclBtn);
        btn_ClearTasks=findViewById(R.id.btn_ClearTascks);
        btn_ClearTasks.setOnClickListener(oclBtn);
    }

    private void initializeList(){
            if(!(dbNote.taskDao().getAll()==null)){
                //Присваиваем локальному списку данные базы
                if(!(dbNote.taskDao().getById(last_id)==null)){
                    l_tasks=dbNote.taskDao().getById(last_id);
                }
            }
    }

    private void initializeRecycleView(){
        try {
            rv_Tasks=findViewById(R.id.rv_Tasks);
            a_tasks=new Adapter_Tasks(getBaseContext(), l_tasks, this);
            rv_Tasks.setAdapter(a_tasks);
            rv_Tasks.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "RV", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void deleteTask(Task task) {
        dbNote.taskDao().delete(task);
        initializeList();
        initializeRecycleView();
    }

    @Override
    public void editTask(Task task) {

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
