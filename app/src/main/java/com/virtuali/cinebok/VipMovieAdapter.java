package com.virtuali.cinebok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.virtuali.cinebok.model.ScheduleVip;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VipMovieAdapter extends RecyclerView.Adapter<VipMovieAdapter.MyViewHolder> {

    Context context;
    ArrayList<ScheduleVip> list;


    public VipMovieAdapter(Context context, ArrayList<ScheduleVip> list) {
        this.context = context;
        this.list = list;
    }

    public VipMovieAdapter(ArrayList<ScheduleVip> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return  new MyViewHolder(v);
    }

//    @Override
//    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position) {
//        SnackBeverage sb = list.get(position);
//        holder.name.setText(sb.getSbName());
//        holder.size.setText(sb.getSbSize());
//        holder.price.setText(sb.getSbPrice());
//        holder.availability.setText(sb.getSbAvailability());
//        Glide.with(holder.img.getContext()).load(sb.getSbUrl()).into(holder.img);
//    }

//    @Override
//    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, int position, @NonNull @NotNull List<Object> payloads) {
//        super.onBindViewHolder(holder, position, payloads);
//    }

    //    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, final int position) {

        ScheduleVip sv = list.get(position);
        holder.Mname.setText("Movie Name: " + sv.getmName());
        holder.Hname.setText("Hall Name: " + sv.gethName());
        holder.duration.setText("Duration: " + sv.getDuration());
        holder.date.setText("Date: " + sv.getDate());
        holder.time.setText("Time" + sv.getTime());
        holder.Tprice.setText("Ticket Price: " + sv.gettPrice());
//        Glide.with(holder.img.getContext()).load(sb.getSbUrl()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Mname, Hname, duration, date, time, Tprice;
        Button book;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Mname = itemView.findViewById(R.id.tv_name_vip_t);
            Hname = itemView.findViewById(R.id.tv_hname_vip_t);
            duration = itemView.findViewById(R.id.tv_duration_vip_t);
            date = itemView.findViewById(R.id.tv_date_vip_t);
            time = itemView.findViewById(R.id.tv_time_vip_t);
            Tprice = itemView.findViewById(R.id.tv_price_vip_t);

            book = itemView.findViewById(R.id.btn_book1_t);


        }
    }

}

