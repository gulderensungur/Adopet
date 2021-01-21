package com.example.adopet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private TextView PetSpecs;
    private ImageView PetPhoto;
    private TextView ChangeRole;
    private ImageButton BottomSheetFilterButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container, false);

        PetSpecs = (TextView) v.findViewById(R.id.txt_pet_name);
        PetPhoto = (ImageView) v.findViewById(R.id.petphoto_1);
        ChangeRole = (TextView) v.findViewById(R.id.chng_role_1);
        BottomSheetFilterButton = (ImageButton) v.findViewById(R.id.filter_button);

        BottomSheetFilterButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               BottomSheetFilter bottomSheet = new BottomSheetFilter();
               bottomSheet.show(getFragmentManager(),"Bottomsheet");
           }
        });


        PetSpecs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),Pet_Profile_forAdopter.class));

            }
        });

        PetPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),Pet_Profile_forAdopter.class));

            }
        });

        ChangeRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),HomeActivity.class));

            }
        });

        return v;

    }
}
