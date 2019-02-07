package com.example.roomtasklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
        viewHolder.tv_About.setText(task.getText());
    }

    @Override
    public int getItemCount() {
        return lData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        Button btn_Delete, btn_Edit;
        TextView tv_Number, tv_About;
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
            btn_Delete=itemView.findViewById(R.id.btn_DeleteTask);
            btn_Edit=itemView.findViewById(R.id.btn_EditTask);
            btn_Edit.setOnClickListener(oclBtn);
            btn_Delete.setOnClickListener(oclBtn);
            tv_Number=itemView.findViewById(R.id.tv_TaskNumber);
            tv_About=itemView.findViewById(R.id.tv_Task);
        }
    }
}