package com.example.loginactivitytest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginactivitytest.R;
import com.example.loginactivitytest.helpers.IdToVenueConverter;
import com.example.loginactivitytest.model.TransportScheduleUser;
import com.example.loginactivitytest.viwholder.RecyclerTransportScheduleViewHolder;

import java.util.List;

 public class RecyclerTransportScheduleAdapter extends RecyclerView.Adapter<RecyclerTransportScheduleViewHolder> {

    private List<TransportScheduleUser> item;

    String id="";
    String fromlocation = "";
    String toLocation = "";
    String transportId = "";

    public RecyclerTransportScheduleAdapter(List<TransportScheduleUser> list){
        this.item=list;
    }

    @Override
    public RecyclerTransportScheduleViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row, null);

        RecyclerTransportScheduleViewHolder recyclerTransportScheduleViewHolder = new RecyclerTransportScheduleViewHolder(layoutView);
        return recyclerTransportScheduleViewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerTransportScheduleViewHolder holder, int position) {

        /*id=String.valueOf(getId());*/
        holder.scheduleId.setText(""+item.get(position).getId());
        holder.timeList.setText(""+item.get(position).getDepartureTime());
        holder.fromLoctionList.setText(IdToVenueConverter.getVenueName(""+item.get(position).getFkFromLocation()));
        holder.toLoctionList.setText(IdToVenueConverter.getVenueName(""+item.get(position).getFkToLocation()));
        holder.transportId.setText(""+item.get(position).getFkTransportId());
    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
