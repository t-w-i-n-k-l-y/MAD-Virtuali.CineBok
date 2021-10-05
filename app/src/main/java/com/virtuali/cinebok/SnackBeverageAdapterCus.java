package com.virtuali.cinebok;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
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
import com.virtuali.cinebok.model.SnackBeverage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

//        if(!count.isEmpty()) {
//
//            Double price = Double.parseDouble(model.getSbPrice());
//            GlobalClass.snackTot += price * Integer.parseInt(count);
//        }

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
        EditText iCount;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name_snack_t);
            size = itemView.findViewById(R.id.tv_size_snack_t);
            price = itemView.findViewById(R.id.tv_price_snack_t);
            availability = itemView.findViewById(R.id.tv_availability_snack_t);
            img=(CircleImageView) itemView.findViewById(R.id.img_snack1_t);
            iCount = itemView.findViewById(R.id.itemCount);
        }
    }

}

