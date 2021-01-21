package com.example.adopet;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyPetFragment extends Fragment  {

    private TextView PetSpecs;
    private ImageView PetPhoto;
    private Button ChngRole;
    private Button CreateAnother;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_pet, container, false);

        PetSpecs = (TextView) v.findViewById(R.id.txt_pet_name);
        PetPhoto = (ImageView) v.findViewById(R.id.petphoto_1);
        ChngRole = (Button) v.findViewById(R.id.button_chng_role);
        CreateAnother = (Button) v.findViewById(R.id.create_another);



        PetSpecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), pet_profile_for_petownerActivity.class));

            }
        });

        PetPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), pet_profile_for_petownerActivity.class));

            }
        });

        ChngRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeActivity.class));

            }
        });

        CreateAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditMyPetActivity.class));

            }
        });


        return v;
    }



}














