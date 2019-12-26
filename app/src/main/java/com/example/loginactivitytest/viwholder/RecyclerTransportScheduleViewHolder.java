package com.example.loginactivitytest.viwholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.loginactivitytest.R;

public class RecyclerTransportScheduleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView timeList, fromLoctionList, toLoctionList, scheduleId, transportId;

    public RecyclerTransportScheduleViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        scheduleId = itemView.findViewById(R.id.scheduleId);
        timeList = itemView.findViewById(R.id.deppartureTimeList);
        fromLoctionList = itemView.findViewById(R.id.fromlocationList);
        toLoctionList = itemView.findViewById(R.id.toloctionList);
        transportId = itemView.findViewById(R.id.transportId);
    }

    @Override
    public void onClick(View v) {

    }
}
