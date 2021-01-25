package com.example.adopet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class CreateProfileActivity extends AppCompatActivity {


    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter adapter;
    private Button addPet;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        listView= (ListView) findViewById(R.id.list_pet);
        addPet = (Button) findViewById(R.id.add_pet);

        arrayList= new ArrayList<String>();

        adapter = new ArrayAdapter(CreateProfileActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = arrayList.size()+1;

                arrayList.add("Pet" + count);
                adapter.notifyDataSetChanged();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                Intent intent = new Intent(CreateProfileActivity.this, AddMyPetActivity.class);
                startActivity(intent);
            }
        });








    }
}