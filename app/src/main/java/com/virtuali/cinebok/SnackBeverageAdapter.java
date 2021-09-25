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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.virtuali.cinebok.model.SnackBeverage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SnackBeverageAdapter extends RecyclerView.Adapter<SnackBeverageAdapter.MyViewHolder> {

    Context context;

    ArrayList<SnackBeverage> list;


    public SnackBeverageAdapter(Context context, ArrayList<SnackBeverage> list) {
        this.context = context;
        this.list = list;
    }

    public SnackBeverageAdapter(ArrayList<SnackBeverage> list) {

        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.snack_item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
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

        SnackBeverage sb = list.get(position);
        holder.name.setText(sb.getSbName());
        holder.size.setText(sb.getSbSize());
        holder.price.setText(sb.getSbPrice());
        holder.availability.setText(sb.getSbAvailability());
        Glide.with(holder.img.getContext()).load(sb.getSbUrl()).into(holder.img);


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_update_snack_list))
                        .setExpanded(true, 1500)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name = view.findViewById(R.id.et_sb_name_t);
                EditText size = view.findViewById(R.id.et_sb_size_t);
                EditText price = view.findViewById(R.id.et_sb_price_t);
                EditText availability = view.findViewById(R.id.et_sb_availability_t);
                EditText url = view.findViewById(R.id.et_sb_url_t);

                Button update = view.findViewById(R.id.btn_update_t);

                name.setText(sb.getSbName());
                size.setText(sb.getSbSize());
                price.setText(sb.getSbPrice());
                availability.setText(sb.getSbAvailability());
                url.setText(sb.getSbUrl());

                dialogPlus.show();


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("sbName", name.getText().toString());
                        map.put("sbSize", size.getText().toString());
                        map.put("sbPrice", price.getText().toString());
                        map.put("sbAvailability", availability.getText().toString());
                        map.put("sbUrl", url.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("SnackBeverage")
                                .child(String.valueOf(getItemId(position))).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show();

                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {
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
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("SnackBeverage")
                                .child(String.valueOf(getItemId(position))).removeValue()
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
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, size, price, availability;
        CircleImageView img;
        //        Button update, delete;
        ImageView edit, delete;

        public MyViewHolder(@NonNull View itemView) {
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
