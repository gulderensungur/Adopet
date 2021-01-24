package com.example.adopet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class MessagesFragment extends Fragment {

    private TextView username;
    private ImageView userimage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_message,container, false);

        username = (TextView) v.findViewById(R.id.user_name_1);
        userimage = (ImageView) v.findViewById(R.id.user_pp);

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),SendMessage.class));

            }
        });

        userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(getActivity(),SendMessage.class));
            }
        });
        return v;
    }
}
