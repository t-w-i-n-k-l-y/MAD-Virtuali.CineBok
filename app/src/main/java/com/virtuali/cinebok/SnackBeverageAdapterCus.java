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
import com.virtuali.cinebok.model.SnackBeverage;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SnackBeverageAdapterCus extends RecyclerView.Adapter<SnackBeverageAdapterCus.MyViewHolder> {

    Context context;

    ArrayList<SnackBeverage> list;


    public SnackBeverageAdapterCus(Context context, ArrayList<SnackBeverage> list) {
        this.context = context;
        this.list = list;
    }

    public SnackBeverageAdapterCus(ArrayList<SnackBeverage> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public SnackBeverageAdapterCus.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.snack_item_cus,parent,false);
        return  new SnackBeverageAdapterCus.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SnackBeverageAdapterCus.MyViewHolder holder, int position) {

        SnackBeverage sb = list.get(position);
        holder.name.setText(sb.getSbName());
        holder.size.setText(sb.getSbSize());
        holder.price.setText(sb.getSbPrice());
        holder.availability.setText(sb.getSbAvailability());
        Glide.with(holder.img.getContext()).load(sb.getSbUrl()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, size, price, availability;
        CircleImageView img;
        ImageView edit,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name_snack_t);
            size = itemView.findViewById(R.id.tv_size_snack_t);
            price = itemView.findViewById(R.id.tv_price_snack_t);
            availability = itemView.findViewById(R.id.tv_availability_snack_t);
            img=(CircleImageView) itemView.findViewById(R.id.img_snack1_t);

//            edit=(ImageView)itemView.findViewById(R.id.editIcon);
//            delete=(ImageView)itemView.findViewById(R.id.deleteIcon);

        }
    }

}

