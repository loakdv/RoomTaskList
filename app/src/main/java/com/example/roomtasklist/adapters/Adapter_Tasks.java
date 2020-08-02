package com.example.roomtasklist.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.roomtasklist.R;
import com.example.roomtasklist.room.entity.Task;

import java.util.List;

public class Adapter_Tasks extends RecyclerView.Adapter<Adapter_Tasks.ViewHolder>{
    private List<Task> lData;
    private LayoutInflater mInflater;
    private Context mContext;

    public interface CallBackButtons{
        void deleteTask(Task task);
        void editTask(Task task);
    }

    CallBackButtons callback;

    public Adapter_Tasks(Context context, List<Task> data, CallBackButtons callback){
        this.mInflater=LayoutInflater.from(context);
        this.lData=data;
        this.callback=callback;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.element_task, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Task task=lData.get(i);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.load_element_anim);
        viewHolder.tv_About.setText(task.getText());
        viewHolder.cardView.startAnimation(animation);
    }



    @Override
    public int getItemCount() {
        return lData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Button btn_Delete, btn_Edit;
        TextView tv_Number, tv_About;
        CardView cardView;
        ViewHolder(final View itemView) {
            super(itemView);

            View.OnClickListener oclBtn=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btn_DeleteTask:
                            callback.deleteTask(lData.get(getLayoutPosition()));
                            break;
                    }
                }
            };
            cardView = itemView.findViewById(R.id.cardTask);
            btn_Delete=itemView.findViewById(R.id.btn_DeleteTask);
            btn_Edit=itemView.findViewById(R.id.btn_EditTask);
            btn_Edit.setOnClickListener(oclBtn);
            btn_Delete.setOnClickListener(oclBtn);
            tv_About=itemView.findViewById(R.id.tv_Task);
        }
    }
}