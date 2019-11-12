package com.example.roomtasklist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Adapter_Notes extends RecyclerView.Adapter<Adapter_Notes.ViewHolder>{
    private List<Note> lData;
    private LayoutInflater mInflater;
    private Context mContext;

    public interface CallBackButtons{
        void deleteNote(Note note);
        void editNote(Note note);
        void open_tasks(Note note);
    }

    DataBase_Note db_Note;

    CallBackButtons callback;

    public Adapter_Notes(Context context, List<Note> data, CallBackButtons callback){
        this.mInflater=LayoutInflater.from(context);
        this.lData=data;
        this.callback=callback;
        this.mContext=context;

        db_Note = Room.databaseBuilder(context,
                DataBase_Note.class, "note_database").allowMainThreadQueries().build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.element_note, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Note note=lData.get(i);
        viewHolder.tv_Name.setText(note.getName());
        viewHolder.tv_About.setText(note.getAbout());
        viewHolder.tv_Date.setText(note.getDate());
        int size=db_Note.taskDao().getSize(note.getId()).size();
        viewHolder.tv_Count.setText(Integer.toString(size));
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.load_element_anim);
        viewHolder.cardView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return lData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Button btn_Delete, btn_Edit, btn_NextNote;
        TextView tv_Name, tv_About, tv_Date, tv_Count;
        CardView cardView;
        ViewHolder(final View itemView) {
            super(itemView);

            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_deleteNote:
                            callback.deleteNote(lData.get(getLayoutPosition()));
                            break;
                        case R.id.btn_nextNote:
                            callback.open_tasks(lData.get(getLayoutPosition()));
                            break;
                    }
                }
            };
            cardView = itemView.findViewById(R.id.cardNote);
            btn_Delete=itemView.findViewById(R.id.btn_deleteNote);
            btn_Delete.setOnClickListener(oclBtn);
            btn_NextNote=itemView.findViewById(R.id.btn_nextNote);
            btn_NextNote.setOnClickListener(oclBtn);
            tv_Name=itemView.findViewById(R.id.tv_NoteName);
            tv_About=itemView.findViewById(R.id.tv_AboutNote);
            tv_Date=itemView.findViewById(R.id.tv_createDate);
            tv_Count=itemView.findViewById(R.id.tv_tasksCount);
        }
    }
}

