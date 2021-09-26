package com.virtuali.cinebok;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.virtuali.cinebok.model.SnackBeverage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SnackBeverageAdapter extends FirebaseRecyclerAdapter<SnackBeverage, SnackBeverageAdapter.myviewholder> {

    public SnackBeverageAdapter(@NonNull FirebaseRecyclerOptions<SnackBeverage> options)
    {
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

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1500)
                        .create();

                View myview=dialogPlus.getHolderView();

                final EditText name = myview.findViewById(R.id.et_sb_name_t);
                final EditText size = myview.findViewById(R.id.et_sb_size_t);
                final EditText price = myview.findViewById(R.id.et_sb_price_t);
                final EditText availability = myview.findViewById(R.id.et_sb_availability_t);
                final EditText url = myview.findViewById(R.id.et_sb_url_t);
                Button update = myview.findViewById(R.id.btn_update_t);

                name.setText(model.getSbName());
                size.setText(model.getSbSize());
                price.setText(model.getSbPrice());
                availability.setText(model.getSbAvailability());
                url.setText(model.getSbUrl());

                dialogPlus.show();

                update.setOnClickListener(new  View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("sbName", name.getText().toString());
                        map.put("sbSize", size.getText().toString());
                        map.put("sbPrice", price.getText().toString());
                        map.put("sbAvailability", availability.getText().toString());
                        map.put("sbUrl", url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(holder.name.getContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updating", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("students")
                                .child(getRef(position).getKey()).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while deleting", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

    } // End of OnBindViewMethod

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.snack_item,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name, size, price, availability;
        CircleImageView img;
        //        Button update, delete;
        ImageView edit, delete;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name_snack_t);
            size = itemView.findViewById(R.id.tv_size_snack_t);
            price = itemView.findViewById(R.id.tv_price_snack_t);
            availability = itemView.findViewById(R.id.tv_availability_snack_t);
            img=(CircleImageView) itemView.findViewById(R.id.img_snack1_t);

//            update = itemView.findViewById(R.id.btn_update_list_t);
//            delete = itemView.findViewById(R.id.btn_delete_record_t);

            edit=(ImageView)itemView.findViewById(R.id.editIcon);
            delete=(ImageView)itemView.findViewById(R.id.deleteIcon);
        }
    }

}
