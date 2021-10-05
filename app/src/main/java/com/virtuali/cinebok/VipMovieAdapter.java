package com.virtuali.cinebok;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.virtuali.cinebok.model.GlobalClass;
import com.virtuali.cinebok.model.ScheduleVip;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        Glide.with(holder.image.getContext()).load(model.getmUrl()).into(holder.image);


        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), VipBookingActivity.class);
                if(model.gettPrice().isEmpty()){
                    Toast.makeText(view.getContext(), "Error!", Toast.LENGTH_SHORT).show();
//                    i.putExtra("ticketPrice", "1000");
                }
                else{
                    i.putExtra("ticketPrice", model.gettPrice());
                    view.getContext().startActivity(i);
                }



            }
        });

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
        ImageView image;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            Mname = itemView.findViewById(R.id.tv_name_vip_t);
            Hname = itemView.findViewById(R.id.tv_hname_vip_t);
            duration = itemView.findViewById(R.id.tv_duration_vip_t);
            date = itemView.findViewById(R.id.tv_date_vip_t);
            time = itemView.findViewById(R.id.tv_time_vip_t);
            Tprice = itemView.findViewById(R.id.tv_price_vip_t);
            image = itemView.findViewById(R.id.iv_img_vip);

            book = itemView.findViewById(R.id.btn_book1_t);


        }
    }

}

