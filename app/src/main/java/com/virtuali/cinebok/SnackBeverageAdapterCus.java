package com.virtuali.cinebok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.virtuali.cinebok.model.SnackBeverage;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SnackBeverageAdapterCus extends FirebaseRecyclerAdapter<SnackBeverage, SnackBeverageAdapterCus.myviewholder> {


    public SnackBeverageAdapterCus(@NonNull FirebaseRecyclerOptions<SnackBeverage> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder, final int position, @NonNull final SnackBeverage model)
    {
        holder.name.setText(model.getSbName());
        holder.size.setText(model.getSbSize());
        holder.price.setText(model.getSbPrice());
        holder.availability.setText(model.getSbAvailability());
        Glide.with(holder.img.getContext()).load(model.getSbUrl()).into(holder.img);




    } // End of OnBindViewMethod

    @NonNull
    @Override
    public SnackBeverageAdapterCus.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.snack_item_cus,parent,false);
        return new SnackBeverageAdapterCus.myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name, size, price, availability;
        CircleImageView img;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name_snack_t);
            size = itemView.findViewById(R.id.tv_size_snack_t);
            price = itemView.findViewById(R.id.tv_price_snack_t);
            availability = itemView.findViewById(R.id.tv_availability_snack_t);
            img=(CircleImageView) itemView.findViewById(R.id.img_snack1_t);

        }
    }

}

