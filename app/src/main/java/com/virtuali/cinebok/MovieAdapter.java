package com.virtuali.cinebok;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MovieAdapter extends FirebaseRecyclerAdapter<Schedule,MovieAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MovieAdapter(@NonNull @NotNull FirebaseRecyclerOptions<Schedule> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, final int position, @NonNull @NotNull Schedule model) {
       holder.movie.setText(model.getMovie());
       holder.hall.setText(model.getHall());
       holder.time.setText(model.getTime());
       holder.duration.setText(model.getDuration());

       holder.btnEdit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final DialogPlus dialogPlus= DialogPlus.newDialog((holder.movie.getContext()))
                       .setContentHolder(new ViewHolder(R.layout.update_shed))
                       .setExpanded(true,1200)
                       .create();
               //dialogPlus.show();
               View view2=dialogPlus.getHeaderView();

               EditText movie= view2.findViewById(R.id.txtmovie);
               EditText hall=view2.findViewById(R.id.txtmhall);
               EditText time=view2.findViewById(R.id.txttime);
               EditText duration=view2.findViewById(R.id.txtduration);

               Button btnUpdate=view.findViewById(R.id.btnUpdate);

               movie.setText(model.getMovie());
               hall.setText(model.getHall());
               time.setText(model.getTime());
               duration.setText(model.getDuration());
               dialogPlus.show();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("Movie",movie.getText().toString());
                        map.put("Hall",hall.getText().toString());
                        map.put("Time",time.getText().toString());
                        map.put("Duration",duration.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("Schedule")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.movie.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {
                                        Toast.makeText(holder.movie.getContext(), "Error", Toast.LENGTH_SHORT).show();

                                    }
                                });


                        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog.Builder builder=new AlertDialog.Builder(holder.movie.getContext());
                                builder.setTitle("Are you Sure?");
                                builder.setMessage("If you delete you can't be undo");

                                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        FirebaseDatabase.getInstance().getReference().child("schudule")
                                                .child(getRef(position).getKey()).removeValue();

                                    }
                                });
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Toast.makeText(holder.movie.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();

                                    }
                                });
                                builder.show();
                            }
                        });


                    }
                });



           }
       });

    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_movie_cardview,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView movie,hall,time,duration;
        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            movie=(TextView)itemView.findViewById(R.id.nametext);
            hall=(TextView)itemView.findViewById(R.id.halltext);
            time=(TextView)itemView.findViewById(R.id.timetext);
            duration=(TextView)itemView.findViewById(R.id.durationtext);

            btnEdit=(Button)itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button)itemView.findViewById(R.id.btnDelete);



        }
    }
}
