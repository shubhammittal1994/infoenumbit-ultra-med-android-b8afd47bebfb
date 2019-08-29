package com.soccermat.ultramed.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soccermat.ultramed.R;


public class AlertasAdapter extends RecyclerView.Adapter<AlertasAdapter.MyViewHolder> {

    Activity activity;

    public AlertasAdapter(Activity activity) {
        this.activity = activity;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_alertas, viewGroup, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

       // myViewHolder.tvFrequency.setText(alertsModels.get(i).getFrequency());
      //  myViewHolder.tvTime.setText(alertsModels.get(i).getStartTime());
    }

    @Override
    public int getItemCount() {

        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvFrequency, tvTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFrequency = itemView.findViewById(R.id.tvFrequency);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}



