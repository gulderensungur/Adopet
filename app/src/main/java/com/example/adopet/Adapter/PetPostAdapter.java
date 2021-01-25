package com.example.adopet.Adapter;
/*
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adopet.Model.Post;
import com.example.adopet.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PetPostAdapter  extends RecyclerView.Adapter<PetPostAdapter.MyHolder>{

    Context context;
    List<Post> postList;

    public PetPostAdapter(Context context, List<Post> popList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_petpost, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String uid = postList.get(position).getUid();
        String mail = postList.get(position).getMail();
        String password = postList.get(position).getPassword();
        String uDp = postList.get(position).getuDp();
        String pId = postList.get(position).getpId();
        String username = postList.get(position).getUsername();
        String pPetOwnerName = postList.get(position).getpPetOwnerName();
        String pPetName = postList.get(position).getpPetName();
        String pPetType = postList.get(position).getpPetType();
        String pOtherFeatures = postList.get(position).getpOtherFeatures();
        String petGender = postList.get(position).getpPetGender();
        String pPetAge = postList.get(position).getpPetAge();
        String pImage = postList.get(position).getpImage();
        String pTimeStamp = postList.get(position).getpTime();


        Calendar calendar= Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

        //set dataa
        holder.uNameTv.setText(username);
        holder.pTimeTv.setText(pTime);
        holder.Pet_Age.setText(pPetAge);
        holder.Pet_Gender.setText(petGender);
        holder.Other_features.setText(pOtherFeatures);
        holder.Pet_Type.setText(pPetType);
        holder.Pet_Name.setText(pPetName);
        holder.Pet_Owner_Name.setText(pPetOwnerName);



        //set user dp
        try{
            Picasso.get().load(uDp).placeholder(R.drawable.avatar).into(holder.uPictureIv);

        }
        catch (Exception e) {

        }

        //set post image
        if (pImage.equals("noImage")){
            holder.pImageIv.setVisibility(View.GONE);

        }
        else {
            try {
                Picasso.get().load(pImage).into(holder.pImageIv);

            } catch (Exception e) {

            }
        }

        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();
            }
        });

        holder.GoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Go", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        //row_petpost views
        ImageView pImageIv, uPictureIv;
        TextView uNameTv, pTimeTv, Pet_Owner_Name, Pet_Name, Pet_Type, Pet_Age, Pet_Gender, Other_features ;
        ImageButton moreBtn;
        Button GoBtn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            uPictureIv = itemView.findViewById(R.id.uPictureIv);
            pImageIv = itemView.findViewById(R.id.pImageIv);
            Pet_Owner_Name = itemView.findViewById(R.id.Pet_Owner_Name);
            Pet_Name = itemView.findViewById(R.id.Pet_Name);
            Pet_Type = itemView.findViewById(R.id.Pet_Type);
            Pet_Age = itemView.findViewById(R.id.Pet_Age);
            Pet_Gender = itemView.findViewById(R.id.Pet_Gender);
            Other_features = itemView.findViewById(R.id.Other_features);
            moreBtn = itemView.findViewById(R.id.moreBtn);
            uNameTv = itemView.findViewById(R.id.uNameTv);
            pTimeTv = itemView.findViewById(R.id.pTimeTv);
            GoBtn = itemView.findViewById(R.id.GoBtn);

        }
    }
}
*/