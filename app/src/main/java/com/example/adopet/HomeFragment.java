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


//    FirebaseAuth mFAuth;
//    DatabaseReference ref;
//
////    RecyclerView recyclerView;
////    List<ModelPetPost> postList;
////    PetPostAdapter petPostAdapter;
//
//    private TextView PetSpecs;
//    private ImageView PetPhoto;
//    private TextView ChangeRole;
//    private ImageButton BottomSheetFilterButton;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home,container, false);
//
//        mFAuth = FirebaseAuth.getInstance();
//
//
//        PetSpecs = (TextView) view.findViewById(R.id.txt_pet_name);
//        PetPhoto = (ImageView) view.findViewById(R.id.petphoto_1);
//        ChangeRole = (Button) view.findViewById(R.id.chng_role_1);
//
////        recyclerView = view.findViewById(R.id.recyclerw);
////        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
////        //show new post first
////        layoutManager.setStackFromEnd(true);
////        layoutManager.setReverseLayout(true);
////
////        recyclerView.setLayoutManager(layoutManager);
////
////        postList = new ArrayList<>();
////
////        loadPosts();
//
//
//        BottomSheetFilterButton.setOnClickListener(new View.OnClickListener(){
//           @Override
//           public void onClick(View v){
//               BottomSheetFilter bottomSheet = new BottomSheetFilter();
//               bottomSheet.show(getFragmentManager(),"Bottomsheet");
//           }
//        });
//
//
////        PetSpecs.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view){
////                startActivity(new Intent(getActivity(),Pet_Profile_forAdopter.class));
////
////            }
////        });
//
////        PetPhoto.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view){
////                startActivity(new Intent(getActivity(),Pet_Profile_forAdopter.class));
////
////            }
////        });
//
//        ChangeRole.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                startActivity(new Intent(getActivity(),HomeActivity.class));
//
//            }
//        });
//
//        return view;
//
//    }
//
//    private void loadPosts() {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Posts");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                postList.clear();
//                for (DataSnapshot ds: snapshot.getChildren()){
//                    ModelPetPost modelPetPost = ds.getValue(ModelPetPost.class);
//
//                    postList.add(modelPetPost);
//
//                    petPostAdapter = new PetPostAdapter(getActivity(), postList);
//
//                    recyclerView.setAdapter(petPostAdapter);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//    }
//
//    }
//
