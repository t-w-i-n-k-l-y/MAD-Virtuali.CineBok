package com.virtuali.cinebok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.virtuali.cinebok.model.ScheduleVip;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VipMovieAdapter extends FirebaseRecyclerAdapter<ScheduleVip,VipMovieAdapter.myviewholder> {


    public VipMovieAdapter(@NonNull FirebaseRecyclerOptions<ScheduleVip> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final ScheduleVip model)
    {
        holder.Mname.setText("Movie Name: " + model.getmName());
        holder.Hname.setText("Hall Name: " + model.gethName());
        holder.duration.setText("Duration: " + model.getDuration());
        holder.date.setText("Date: " + model.getDate());
        holder.time.setText("Time: " + model.getTime());
        holder.Tprice.setText("Ticket Price: " + model.gettPrice());
//        Glide.with(holder.img.getContext()).load(sb.getSbUrl()).into(holder.img);

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);
        return new myviewholder(view);
    }


    public static class myviewholder extends RecyclerView.ViewHolder{

        TextView Mname, Hname, duration, date, time, Tprice;
        Button book;

        public myviewholder(@NonNull View itemView) {
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

